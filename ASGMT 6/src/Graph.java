import java.util.*;

public class Graph implements GraphInterface<Town, Road> {

	private Set<Town> towns;
	private Set<Road> roads;
	
	private Map<Town, HashSet<Town>> adjTowns;
	
	private Map<Town, Integer> distances;
	private Map<Town, LinkedList<Town>> shortestPaths;
	
	public Graph() {
		towns = new HashSet<>();
		roads = new HashSet<>();
		adjTowns = new HashMap<>();
	}
	
	@Override
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		for (Road rd : roads) //for each road
		{
			if (rd.contains(sourceVertex) && rd.contains(destinationVertex)) //if it contains the source and destination
			{
				return rd; //return the road
			}
		}
		return null; //otherwise return empty
	}

	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		if (!containsVertex(sourceVertex) || !containsVertex(destinationVertex)) //if the source or destination are not in towns
		{
			throw new IllegalArgumentException(); //this should not exist
		}
		if (sourceVertex == null || destinationVertex == null) //if the source or destination are empty
		{
			throw new NullPointerException(); //return that its empty
		}
		
		Road edge = new Road(sourceVertex, destinationVertex, weight, description); //otherwise, create the road

		adjTowns.get(destinationVertex).add(sourceVertex); //and add it to the map connecting source to the destination
		adjTowns.get(sourceVertex).add(destinationVertex); //and vice versa (connect destination to source)

		roads.add(edge); //add the road to the list
		return edge;
	}

	@Override
	public boolean addVertex(Town v) {
		if (v == null) //if v is empty
		{
			throw new NullPointerException(); //say its empty
		}

		if (!adjTowns.containsKey(v)) //if v is not part of the map
		{
			adjTowns.put(v, new HashSet<Town>()); //add it to the map
			towns.add(v); //and add it to the list
			return true; //return that it was added
		}
		else //otherwise
		{
			return false; //return that it was not added
		}
	}

	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		return getEdge(sourceVertex, destinationVertex) != null; //return if there is an edge/ if edge is not empty
	}

	@Override
	public boolean containsVertex(Town v) {
		for (Town town : towns) //for each town
		{
			if (town.equals(v)) //if it contains town v
			{
				return true; //return that it contains v
			}
		}
		return false; //otherwise return it does not contain v
	}

	@Override
	public Set<Road> edgeSet() {
		return roads; //return the list of roads in the set
	}

	@Override
	public Set<Road> edgesOf(Town vertex) {
		if (vertex == null) //if there is no town
		{
			throw new NullPointerException(); //say there is no town
		}
		
		if (!containsVertex(vertex)) //if the town is not in the list
		{
			throw new IllegalArgumentException(); //say its not in the list
		}
		
		Set<Road> townEdges = new HashSet<>(); //create a new list
		
		for (Road rd : roads) //for each road in the roads list
		{
			if (rd.contains(vertex)) //if the road contains the town
			{
				townEdges.add(rd); //add the road to the new list
			}
		}
		return townEdges; //return the new list
	}

	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		if (adjTowns.containsKey(sourceVertex)) //if source is part of the map
		{
			if (adjTowns.get(sourceVertex).contains(destinationVertex)) //and if destination is connected to source
			{
				adjTowns.get(sourceVertex).remove(destinationVertex); //remove the destination
			}
		}

		if (adjTowns.containsKey(destinationVertex)) //and vice versa
		{
			if (adjTowns.get(destinationVertex).contains(sourceVertex))
			{
				adjTowns.get(destinationVertex).remove(sourceVertex);
			}
		}

		for (Road rd : roads) //for each road in the list
		{
			if (rd.getName().equals(description)) //if the road is the same as the description
			{
				roads.remove(rd); //remove the road from the list
				return rd; //and return the road data
			}
		}
		return null; //otherwise, return nothing
	}

	@Override
	public boolean removeVertex(Town v) {
		if (adjTowns.containsKey(v) && towns.contains(v)) //if the town is on the map and the town list contains the town
		{
			for (Town town : adjTowns.get(v)) //for each town that is connected to v
			{
				Road rd = getEdge(v, town); //get the road that connects v to the town
				if (rd != null) //and if the road is not empty
				{
					roads.remove(rd); //remove it from the list
				}
			}
			adjTowns.remove(v); //then remove the town from the map
			towns.remove(v); //and remove the town from the town list

			return true; //let the program the town is removed
		}
		return false; //otherwise, let it know it was not removed
	}

	@Override
	public Set<Town> vertexSet() {
		return towns; //return the list containing all the towns
	}

	@Override
	public ArrayList shortestPath(Town sourceVertex, Town destinationVertex) {
		ArrayList<String> list = new ArrayList<>(); //create a list

		dijkstraShortestPath(sourceVertex); //put the town into the algorithm
		LinkedList<Town> shortPath = shortestPaths.get(destinationVertex); //get the paths to the destination

		if (shortPath.isEmpty()) //if the paths are empty
		{
			return list; //return the list
		}

		Town prevTown = shortPath.get(0); //get the previous town
		for (int i = 1; i < shortPath.size(); i++) //for each path
		{
			Town current = shortPath.get(i); //get the town connected to it

			if (prevTown != null && current != null) //if both towns exist
			{
				Road road = getEdge(prevTown, current); //get the road connecting them and add to the list
				list.add(prevTown + " via " + road + " to " + current + " " + road.getWeight() + " mi ");
			}
			prevTown = current; //move to the the next town within the paths list
		}
		Road road = getEdge(prevTown, destinationVertex); //get the road between the last town and the destination
		list.add(prevTown + " via " + road + " to " + destinationVertex + " " + road.getWeight() + " mi "); //add it to the list

		return list; //return the list
	}

	@Override
	public void dijkstraShortestPath(Town sourceVertex) {
		if (sourceVertex == null) //if the town does not exist
		{
			throw new NullPointerException();
		}
		
		distances = new HashMap<>();
		shortestPaths = new HashMap<>();

		for (Town town : towns) //for each town in the list
		{
			distances.put(town, Integer.MAX_VALUE); //add it to a spot in the distances map
			shortestPaths.put(town, new LinkedList<>()); //and add it to a spot in the linked list
		}
		
		distances.put(sourceVertex, 0); //add the source as the first vertex

		Set<Town> colonized = new HashSet<>(); //visited towns
		Set<Town> vacant = new HashSet<>(); //towns yet to visit

		vacant.add(sourceVertex); //add the source to visited towns
		
		while (vacant.size() != 0) //while there are still towns to visit
		{
			Town current = getShortest(vacant); //get the closest town that hasn't been visited
			vacant.remove(current); //and remove it from the list

			for (Town adjTown : adjTowns.get(current)) //for each adj town connected to the current
			{
				int distance = getEdge(current, adjTown).getWeight(); //get the distance of the road

				if (!colonized.contains(adjTown)) //and if its not in the visited list
				{
					calcDistance(adjTown, distance, current); //calculate the distance
					vacant.add(adjTown); //and add the town to the unvisited list
				}
			}
			colonized.add(current); //add closest town to visited list
		}
	}

	private Town getShortest(Set<Town> vacant) {

		Town closest = null;
		int dist = Integer.MAX_VALUE; //distance is set to over the top max

		for (Town town : vacant) //for each town in the list
		{
			int townDist = distances.get(town); //get the distance from that town
			if (townDist < dist) //if that distance is less than what what is held
			{
				dist = townDist; //it becomes the new distance
				closest = town; //and save the town
			}
		}
		return closest; //return the town with closest distance
	}

	private void calcDistance(Town adj, int dist, Town current) {

		int currentDist = distances.get(current); //get the distance from current town

		if (currentDist + dist < distances.get(adj)) //if combined distance is less than the distance to the adj town
		{
			distances.put(adj, currentDist + dist); //add in the combined distance for the adj town
			
			LinkedList<Town> shortPath = new LinkedList<>(shortestPaths.get(current)); //create a spot within a linked list for the current towns paths
			
			shortPath.addLast(current); //and add to the new linked list
			shortestPaths.put(adj, shortPath); //then add all info to the traveled map
		}
	}
}

