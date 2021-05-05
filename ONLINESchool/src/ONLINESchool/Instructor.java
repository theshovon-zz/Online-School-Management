package ONLINESchool;

/*
 * Each instructor has their name, campus phone extension (e.g., 70310), and contact email. 
 */
public class Instructor {
	/*
	 * Attributes
	 */
	private String name;
	private String email;
	private int extension;
	
	/*
	 * Constructors
	 */
	public Instructor(String name, int extension, String email) {
		this.name = name;
		this.extension = extension;
		this.email = email;
	}
	
	/*
	 * Mutators
	 */ 
	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhoneExtension(int extension) {
		this.extension = extension;
	}
	
	/*
	 * Accessors
	 */
	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public int getPhoneExtension() {
		return extension;
	}
	
	public String getInformation() {
		String result = "";
		result = "Instructor " + this.name + " has campus phone extension " + this.extension + " and contact email " + this.email;
		return result;
	} 
}