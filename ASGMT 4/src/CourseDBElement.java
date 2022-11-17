
public class CourseDBElement {

	protected String id, roomNum, instructor;
	protected int crn, credits;
	
	/**
	 * 
	 * @param ID  The ID of the course
	 * @param CRN  The CRN of the course
	 * @param creds  The amount of credits of the course
	 * @param room  The room number of the course
	 * @param name  The name of the instructor of the course
	 */
	CourseDBElement(String ID, int CRN, int creds, String room, String name) {
		id = ID;
		crn = CRN;
		credits = creds;
		roomNum = room;
		instructor = name;
	}
	
	/**
	 * 
	 * @return The course ID
	 */
	public String getID() {
		return id;
	}
	/**
	 * 
	 * @param ID The ID of the course
	 */
	public void setID(String ID) {
		id = ID;
	}
	
	/**
	 * 
	 * @return The course CRN
	 */
	public int getCRN() {
		return crn;
	}
	/**
	 * 
	 * @param CRN The CRN of the course
	 */
	public void setCRN(int CRN) {
		crn = CRN;
	}
	
	/**
	 * 
	 * @return the amount of credits for the course
	 */
	public int getCredits() {
		return credits;
	}
	/**
	 * 
	 * @param creds the amount of credits for the course
	 */
	public void setCredits(int creds) {
		credits = creds;
	}
	
	/**
	 * 
	 * @return The course room number
	 */
	public String getRoomNum() {
		return roomNum;
	}
	/**
	 * 
	 * @param room The room number the course is held in
	 */
	public void setRoomNum(String room) {
		roomNum = room;
	}
	
	/**
	 * 
	 * @return the instructor of the course
	 */
	public String getInstructor() {
		return instructor;
	}
	/**
	 * 
	 * @param name The name of the instructor of the course
	 */
	public void setInstructor(String name) {
		instructor = name;
	}
	
	@Override
	public String toString () {
		String courseString = "";
		
		courseString += "\nCourse:" + this.getID() + " CRN:" + this.getCRN() + " Credits:" + this.getCredits() + " Instructor:" + this.getInstructor() + " Room:" + getRoomNum();
		
		return courseString;
	}
	

	public int hashCode() {
		//String crnString = String.valueOf(getCRN());
		//int courseHash = crnString.hashCode();
		int courseHash = (Integer.valueOf(getCRN())).hashCode();
		return courseHash;
	}
	
	public boolean compareTo(CourseDBElement cdbe) {
		if (this.getCRN() == cdbe.getCRN())
		{
			return true;
		}
		else return false;
	}

	
}
