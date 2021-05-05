package ONLINESchool;

/*
 * Each participant may be registered in up to 5 courses. 
 */
public class Participant {
	
	/*
	 * Attributes
	 */
	private String name;
	private final int MAX_NUM_REGISTRATIONS = 5;
	
	private Registration[] registrations; 
	/* 
	 * 1. value of nor represents the number of Registration objects stored in the registrations array.
	 * 2. value of nor denotes where in the array 'registrations' is the next Registration object to be stored.
	 */
	private int nor; 
	
	/*
	 * Constructors
	 */

	public Participant(String name) {
		this.name = name;
		this.registrations = new Registration[MAX_NUM_REGISTRATIONS];
		this.nor = 0; // so far zero courses registered; the next course is to be stored at index 0
	}
	
	/*
	 * Mutators
	 */
	
	/* Version 1: Given a Registration object, store it directly into the registrations array. */
	public void addRegistration(Registration r) {
		if(this.nor < MAX_NUM_REGISTRATIONS) {
			this.registrations[this.nor] = r;
			this.nor ++;
		}
	}

	/* Version 2: Given a title, which is sufficient to create a new Registration object, add it to the registrations array. */
	public void addRegistration(String title) {
		Registration r = new Registration(title);
		this.addRegistration(r);
	}
	
	public void clearRegistrations() {
		this.nor = 0; /* there is no need to assign each slot of the `registrations` array to null */
	}
	
	/*
	 * Given the title of course, set the marks of that course.
	 * If the course does not exist, do nothing.
	 */
	public void updateMarks(String title, int marks) {
		int index = this.indexOf(title);
		if(index >= 0) {
			this.registrations[index].setMarks(marks);
		}
	}
	
	/*
	 * Accessors
	 */
	public Registration[] getRegistrations() {
		Registration[] result = new Registration[this.nor];
		for(int i = 0; i < this.nor; i ++) {
			result[i] = this.registrations[i];
		}
		return result;
	} 
	
	/*
	 * Given the title of course, return the marks of that course.
	 * If the course does not exist, return -1
	 */
	public int marksOf(String title) {
		int marks = -1;

		int index = this.indexOf(title);
		if(index >= 0) {
			marks = this.registrations[index].getMarks();
		} 

		return marks;
	}

	// Helper method reused by getMarks and setMarks
	// Given the title of course, return the index of the corresponding registration object.
	// If the title does not exists, return -1.
	private int indexOf(String title) {
		int index = -1;

		boolean found = false;
		for(int i = 0; i < this.nor && !found; i ++) {
			if(this.registrations[i].getTitle().equals(title)) {
				found = true;
				index = i;
			}
		}

		return index;
	}
	
	/*
	 * (Not required by the JUnit test)
	 * Method used by `getStudents` of OnlineSchool
	 */
	boolean isRegistered(String title) {
		return indexOf(title) >= 0;
	}
	
	public String getGPAReport() {
		String report;
		
		if(this.nor > 0) {
			report = "{";
			double gpa = 0.0;
			int totalGP = 0;
			for(int i = 0; i < this.nor; i ++) {
				Registration r = this.registrations[i]; 
				int gp = r.getGradePoint();
				String lg = r.getGradeReport()[0];
				report += gp + " (" + lg + ")";
				if(i < this.nor - 1) {
					report += ", ";
				}
				totalGP += gp; 
			}
			report += "}";
			
			gpa = (double) totalGP / this.nor;
			report = this.name + "'s GPA of " + report + ": " + String.format("%.1f", gpa);			
		}
		else {
			report = "No GPA available yet for " + this.name;
		}
		
		return report;
	}
}
