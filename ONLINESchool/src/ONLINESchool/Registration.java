package ONLINESchool;

/*
 * Each registration is characterized by the title, marks, and instructor. 
 */
public class Registration {
	
	/*
	 * Attributes
	 */
	private String title; // notice that String is not a primitive type, it is a reference type.
	private int marks; /* final raw marks out of 100 */
	private Instructor instructor; /* stores the address of some Instructor object */ 
	
	/*
	 * Constructors
	 */
	public Registration(String title) {
		this.title = title;
	}
	
	/*
	 * Mutators
	 */
	public Registration(String title, Instructor instructor) {
		this.title = title;
		this.instructor = instructor; 
	}
	
	public void setMarks(int marks) {
		this.marks = marks;
	}
	
	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}
	
	/*
	 * Accessors
	 */
	public String getTitle() {
		return title;
	}

	public int getMarks() {
		return marks;
	}

	public Instructor getInstructor() {
		return instructor;
	}  
	
	/*
	 * (Not required by the JUnit)
	 * Method used by `getGPAReport` of Participant.
	 * Neither private nor public means the method is visible to classes in the same package.
	 */
	int getGradePoint() { 
		int gp = 0;
		
		if(this.marks >= 90) {
			gp = 9;
		}
		else if(this.marks >= 80) { 
			gp = 8;
		}
		else if(this.marks >= 70) { 
			gp = 7;
		}
		else if(this.marks >= 60) { 
			gp = 6;
		}
		else if(this.marks >= 50) { 
			gp = 5;
		}
		else { // < 50 
			gp = 0;
		} 
		 
		return gp;
	}
	
	public String[] getGradeReport() {
		String lg = "";
		String des = ""; 
		
		if(this.marks >= 90) {
			lg = "A+";
			des = "Exceptional"; 
		}
		else if(this.marks >= 80) {
			lg = "A";
			des = "Excellent"; 
		}
		else if(this.marks >= 70) {
			lg = "B";
			des = "Good"; 
		}
		else if(this.marks >= 60) {
			lg = "C";
			des = "Competent"; 
		}
		else if(this.marks >= 50) {
			lg = "D";
			des = "Passing"; 
		}
		else { // < 50
			lg = "F";
			des = "Failing"; 
		} 
		
		String[] report = {lg, des};
		return report;
	}
	
	public String getInformation() {
		String desc = "";
		if(this.instructor == null) {
			desc = this.title + " has not yet been assigned an instructor";
		}
		else { 
			String[] report = this.getGradeReport();
			String lg = report[0];
			String des = report[1];
			desc = this.title + ", taught by " + this.instructor.getName() + ", is completed with raw marks " + this.marks + " (" + lg + " ; " + des + ")";
		}
		return desc;
	}
}