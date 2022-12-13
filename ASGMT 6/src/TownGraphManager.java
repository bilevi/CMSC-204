import java.util.*;

public class TownGraphManager implements TownGraphManagerInterface {

	private Graph townGraph;

	
	public TownGraphManager() {
		townGraph = new Graph();
	}
	
	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		try {
			Town townOne = new Town(town1); //create town 1
			Town townTwo = new Town(town2); //create town 2
			
			return townGraph.addEdge(townOne, townTwo, weight, roadName) != null; //return if it was added or not
			
		} catch (Exception e) { //catch the exceptions
			e.getMessage(); //get what kind of exception
			return false; //and return it was not added
		}
	}

	@Override
	public String getRoad(String town1, String town2) {
		Town source = getTown(town1); //create town 1
		Town dest = getTown(town2); //and town 2
		
		Road rd = townGraph.getEdge(source, dest); //get the road connecting both towns
		
		return rd.toString(); //return the toString
	}

	@Override
	public boolean addTown(String v) {
		try {
			Town Vtown = new Town(v); //create the town
			
			return townGraph.addVertex(Vtown); //return if the town was added or not
			
		} catch (NullPointerException e) { //catch the exception
			e.getMessage(); //get what kind of exception
			return false; //return the town was not added
		}
	}

	@Override
	public Town getTown(String name) {
		Iterator<Town> iter = townGraph.vertexSet().iterator(); //create an iterator for the towns
		
		while (iter.hasNext()) //while there is another town to go to
		{
			Town town = iter.next(); //get the next town
			if (town.getName().equals(name)) //and if this town is the same as the town we want
			{
				return town; //return this town
			}
		}
		return null; //otherwise, return empty
	}

	@Override
	public boolean containsTown(String v) {
		return getTown(v) != null; //return if the town exists
	}

	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		Town source = getTown(town1); //create town 1
		Town dest = getTown(town2); //and town 2
		
		return townGraph.containsEdge(source, dest); //return if there is a road connecting both towns
	}

	@Override
	public ArrayList<String> allRoads() {
		ArrayList<String> list = new ArrayList<String>(); //create a new list

		for (Road rd : townGraph.edgeSet()) //for each road on the map
		{
			list.add(rd.toString()); //add the toString to the list
		}
		Collections.sort(list); //sort it! Yay exam questions!
		return list; //and return the list
	}

	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		Town source = getTown(town1); //and creating again
		Town dest = getTown(town2); //both towns

		Road rd = townGraph.getEdge(source, dest); //getting the road that connects both

		return townGraph.removeEdge(source, dest, rd.getWeight(), rd.toString()) != null; //and return if the road was deleted
	}

	@Override
	public boolean deleteTown(String v) {
		Town Vtown = getTown(v); //get the town
		
		return townGraph.removeVertex(Vtown); //and return if it was deleted
	}

	@Override
	public ArrayList<String> allTowns() {
		ArrayList<String> list = new ArrayList<>(); //creating a list

		for (Town town : townGraph.vertexSet()) //and for each town on the map
		{
			list.add(town.toString()); //add the toString to the list
		}

		Collections.sort(list); //sort it!
		return list; //and return all those toStrings
	}

	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		Town source = getTown(town1); //creating again
		Town dest = getTown(town2); //and again
		
		return townGraph.shortestPath(source, dest); //return the shortest path between both towns
	}
}
