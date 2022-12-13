import java.util.*;

/*
 * Class: CMSC 204
 * Instructor: Monshi
 * Description: A program that creates a map of towns and roads and traverses it, finding the shortest path
 * Due: 12/13/2022
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming assignment independently.
   I have not copied the code from a student or any source.
   I have not given my code to any student.
   Print your Name here: B. Leigh Vining
 */
/**
 * 
 * @author B. Leigh Vining
 *
 */
public class Town implements Comparable<Town>{

	private String name;
	
	/**
	 * 
	 * @param name  Name of the town
	 */
	public Town(java.lang.String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * @param templateTown  Town to be copied
	 */
	public Town(Town templateTown) {
		this(templateTown.getName());
		
	}
	
	/**
	 * 
	 * @return  Name of the town
	 */
	public String getName() {
		return name;
	}
	
	@Override
	public int compareTo(Town o) {
		if (this.getName().equals(o.getName()))
		{
			return 0;
		}
		else return -1;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Town))
		{
			return false;
		}
		else
		{
			return this.getName().equals(((Town) obj).getName());
		}
	}
}
