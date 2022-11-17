import java.io.*;
import java.util.*;

public class CourseDBStructure implements CourseDBStructureInterface {
	
	protected LinkedList<CourseDBElement>[] hashTable;
	protected int tableSize;
	
	CourseDBStructure(int size) {
		tableSize = getPrime(size);
		hashTable = new LinkedList[tableSize];
	}
	
	CourseDBStructure(String test, int size) {
		tableSize = size;
		hashTable = new LinkedList[tableSize];
		
	}
	
	@Override
	public void add(CourseDBElement element) {
		int courseHash = element.hashCode();
		int index = courseHash % tableSize;
		boolean exists = false;
		
		if (hashTable[index] == null)
		{
			hashTable[index] = new LinkedList<CourseDBElement>();
			hashTable[index].add(element);
		}
		
		else {
			LinkedList<CourseDBElement> dataIndex = hashTable[index];
			for (CourseDBElement course : dataIndex)
			{
				while (!exists)
				if (course.compareTo(element))
				{
					exists = true;
					course.setID(element.getID());
					course.setCRN(element.getCRN());
					course.setCredits(element.getCredits());
					course.setRoomNum(element.getRoomNum());
					course.setInstructor(element.getInstructor());
				}
			}
			
			if (!exists)
			{
				hashTable[index].add(element);
			}
		}
	}
	
	@Override
	public CourseDBElement get(int crn) throws IOException {
		//int index = ((String.valueOf(crn)).hashCode()) % tableSize;
		int index = ((Integer.valueOf(crn)).hashCode()) % tableSize;
		CourseDBElement tempCourse = new CourseDBElement("", crn, 0, "", "");
		
		if (hashTable[index] != null)
		{
			LinkedList<CourseDBElement> dataIndex = hashTable[index];
			for (CourseDBElement course : dataIndex)
			{
				if (course.compareTo(tempCourse) == true)
				{
					return course;
				}
			}
		}
		throw new IOException();
	}
	
	@Override
	public ArrayList<String> showAll() {
		ArrayList<String> courseStringArray = new ArrayList<String>();
		
		for (int i = 0; i < hashTable.length; i++)
		{
			LinkedList<CourseDBElement> buckets = hashTable[i];
			if (buckets != null)
			{
				for (CourseDBElement course : buckets)
				{
					courseStringArray.add(course.toString());
				}
			}
		}
		
		return courseStringArray;
	}
	
	@Override
	public int getTableSize() {
		return this.tableSize;
	}
	/**
	 * 
	 * @param num The table size number
	 * @return The next 4k+3 prime number after num
	 */
	public int getPrime(int num) {
		double loadFactor = 1.5;
		boolean fkp3 = false;
		boolean aPrime = false;
		int prime, highDivisor, d;
		
		prime = (int)(num/loadFactor);
		if (prime % 2 == 0)
		{
			prime = prime + 1;
		}
		
		while (fkp3 == false)
		{
			while (aPrime == false)
			{
				highDivisor = (int)(Math.sqrt(prime) + 0.5);
				for (d = highDivisor; d>1; d--)
				{
					if (prime % d == 0)
					{
						break;
					}
				}
				if (d != 1)
				{
					prime = prime + 2;
				}
				else aPrime = true;
				}
				
			if ((prime -3) % 4 == 0)
			{
				fkp3 = true;
			}
			else
			{
				prime = prime + 2;
				aPrime = false;
			}
		}
		return prime;
	}
 
}
