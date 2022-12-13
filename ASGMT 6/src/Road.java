
public class Road implements Comparable<Road> {

	private String name;
	private int distance;
	private Town townFrom;
	private Town townTo;
	
	/**
	 * 
	 * @param source  The starting town
	 * @param destination  The town to go to
	 * @param degrees  The distance/weight of the road
	 * @param name  The name of the road
	 */
	public Road(Town source, Town destination, int degrees, String name) {
		townFrom = source;
		townTo = destination;
		distance = degrees;
		this.name = name;
	}
	
	/**
	 * 
	 * @param source  The starting town
	 * @param destination  The town to go to
	 * @param name  The name of the road
	 */
	public Road(Town source, Town destination, String name) {
		this(source, destination, 1, name);
	}
	
	/**
	 * 
	 * @param town  The town that is to be compared with
	 * @return  if this town is the same as the town to be compared with
	 */
	public boolean contains (Town town) {
		return this.getSource().equals(town) || this.getDestination().equals(town);
	}
	
	@Override
	public String toString() {
		return this.getName();
	}
	
	/**
	 * 
	 * @return  the name of the road
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @return  The town that the road leads to
	 */
	public Town getDestination() {
		return townTo;
	}
	
	/**
	 * 
	 * @return the town that the road leaves
	 */
	public Town getSource() {
		return townFrom;
	}
	
	@Override
	public int compareTo(Road o) {
		if (this.getSource().equals(o.getSource()) && this.getDestination().equals(o.getDestination()))
		{
			return 0;
		}
		else return -1;
	}
	
	/**
	 * 
	 * @return  The distance/degrees of the road
	 */
	public int getWeight() {
		return distance;
	}
	
	@Override
	public boolean equals(Object r) {
		if(!(r instanceof Road))
		{
			return false;
		}
		else
		{
			Road rd = (Road) r;
			return (this.getSource().equals(rd.getSource()) && this.getDestination().equals(rd.getDestination()));
		}
	}
}
