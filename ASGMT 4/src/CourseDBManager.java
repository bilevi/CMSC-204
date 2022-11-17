import java.io.*;
import java.util.*;

/*
 * Class: CMSC 204
 * Instructor: Monshi
 * Description: A program that creates a database of courses.
 * Due: 11/16/2022
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
public class CourseDBManager implements CourseDBManagerInterface {
	
	private CourseDBStructure hashStructure;
	
	CourseDBManager() {
		hashStructure = new CourseDBStructure(20);
	}
	
	@Override
	public void add(String id, int crn, int credits, String roomNum, String instructor) {
		CourseDBElement course = new CourseDBElement(id, crn, credits, roomNum, instructor);
		hashStructure.add(course);
	}

	@Override
	public CourseDBElement get(int crn) {
		CourseDBElement course;
		
		try {
			return hashStructure.get(crn);
		} catch (IOException e)
		{
			e.getMessage();
			return null;
		}
	}
	
	@Override
	public void readFile(File input) throws FileNotFoundException {
		try {
			Scanner file = new Scanner(input);
			
			while (file.hasNext())
			{
				String line = file.nextLine();
				String[] words = line.split(" ");
				
				String id = words[0];
				int crn = Integer.parseInt(words[1]);
				int creds = Integer.parseInt(words[2]);
				String room = words[3];
				String name = "";
				
				for (int i = 4; i < words.length; i++)
				{
					name += words[i];
				}
				
				hashStructure.add(new CourseDBElement(id, crn, creds, room, name));
			}
		} catch (FileNotFoundException e)
		{
			e.getMessage();
		}
		
		
	}

	@Override
	public ArrayList<String> showAll() {
		ArrayList<String> courseStringArray = new ArrayList<String>();
		
		for (int i = 0; i < hashStructure.getTableSize(); i++)
		{
			LinkedList<CourseDBElement> buckets = hashStructure.hashTable[i];
			if (buckets != null)
			{
				for (CourseDBElement course : buckets)
				{
					courseStringArray.add(course.toString());
					System.out.println(course.toString());
				}
			}
		}
		return courseStringArray;
	}
	
}
