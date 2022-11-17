import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CourseDBManager_STUDENT_Test {

	private CourseDBManagerInterface dataMngr = new CourseDBManager();
	private CourseDBElement course1, course2;
	
	@BeforeEach
	void setUp() throws Exception {
		dataMngr = new CourseDBManager();
		course1 = new CourseDBElement("CMSC204", 22566, 4, "Zoom", "Monshi");
		course2 = new CourseDBElement("ARTT123", 20056, 3, "Paul Peck 302", "Derickson");
	}

	@AfterEach
	void tearDown() throws Exception {
		dataMngr = null;
	}

	@Test
	void testAdd() {
		try {
			dataMngr.add("CMSC204", 22566, 4, "Zoom", "Monshi");
		} catch (Exception e)
		{
			fail("This should not have caused/thrown an exception");
		}
	}

	@Test
	void testGet() {
		dataMngr.add("CMSC204", 22566, 4, "Zoom", "Monshi");
		dataMngr.add("ARTT123", 20056, 3, "Paul Peck 302", "Derickson");
		try {
			assertEquals(22566, dataMngr.get(course1.getCRN()).getCRN());
		} catch (Exception e)
		{
			fail("This should not have caused/thrown an exception");
		}
		
	}

	@Test
	void testReadFile() {
		try {
			File inputFile = new File("StudentTest.txt");
			PrintWriter inFile = new PrintWriter(inputFile);
			inFile.println("CMSC203 34165 4 Distance-Learning Grigoriy Grinberg");
			inFile.println("CMSC214 35968 3 Zoom Christopher Fallon");
			inFile.print("CMSSC220 35646 3 Remote Ashique Tanveer");
			
				
			inFile.close();
			dataMngr.readFile(inputFile);
			assertEquals(4, dataMngr.get(34165).getCredits());
			assertEquals("CMSC214", dataMngr.get(35968).getID());
			assertEquals("Remote", dataMngr.get(35646).getRoomNum());
		} catch (Exception e)
		{
			fail("This should not have caused/thrown an exception");
		}
	}

	@Test
	void testShowAll() {
		dataMngr.add("CMSC204", 22566, 4, "Zoom", "Monshi");
		dataMngr.add("ARTT123", 20056, 3, "Paul Peck 302", "Derickson");
		ArrayList<String> list = dataMngr.showAll();
		assertEquals(list.get(0), "\nCourse:ARTT123 CRN:20056 Credits:3 Instructor:Derickson Room:Paul Peck 302");
	 	assertEquals(list.get(1), "\nCourse:CMSC204 CRN:22566 Credits:4 Instructor:Monshi Room:Zoom");
	}

}
