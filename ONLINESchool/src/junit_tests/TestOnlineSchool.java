package junit_tests;

import static org.junit.Assert.*;

import org.junit.Test;

import ONLINESchool.Instructor;
import ONLINESchool.OnlineSchool;
import ONLINESchool.Participant;
import ONLINESchool.Registration;

public class TestOnlineSchool {
	
	@Test
	public void test_01() {
		Instructor i = new Instructor("Jackson", 70130, "Jackson@eecs.yorku.ca");
		String name = i.getName();
		int ext = i.getPhoneExtension();
		String contact = i.getEmail();
		String info = i.getInformation();
		
		assertEquals("Jackson", name);
		assertEquals(70130, ext);
		assertEquals("Jackson@eecs.yorku.ca", contact);
		assertEquals("Instructor Jackson has campus phone extension 70130 and contact email Jackson@eecs.yorku.ca", info);
		
		i.setName("Jonathan");
		i.setPhoneExtension(70139);
		i.setEmail("jonathan@yorku.ca");
		
		assertEquals("Jonathan", i.getName());
		assertEquals(70139, i.getPhoneExtension());
		assertEquals("jonathan@yorku.ca", i.getEmail());
		assertEquals("Instructor Jonathan has campus phone extension 70139 and contact email jonathan@yorku.ca", i.getInformation());
	}
	
	/*
	 * Recommended exercises: 
	 * 	Visualizing and tracing (on both debugger and paper) on how objects are created and manipulated
	 * 	in each test would be extremely valuable for reinforcing your understanding.
	 */ 
	@Test
	public void test_02a() {
		Registration r = new Registration("Software Design");
		String t = r.getTitle();
		int m = r.getMarks();
		Instructor i = r.getInstructor();
		/* Size of returned array should be 2: 
		 * 	- 1st element denotes the letter grade.
		 * 	- 2nd element denotes its qualitative description (see the mapping table in the instructions PDF). 
		 */
		String[] gr = r.getGradeReport(); 
		/* Returned information only displays the marks, grade, and description when there is an instructor. */
		String info = r.getInformation(); 
		
		assertEquals("Software Design", t);
		assertEquals(0, m); 
		assertNull(i);
		assertTrue(gr.length == 2 && gr[0].equals("F") && gr[1].equals("Failing"));
		assertEquals("Software Design has not yet been assigned an instructor", info);
		
		Instructor Jackson = new Instructor("Jackson", 70130, "Jackson@eecs.yorku.ca");
		r.setInstructor(Jackson); 
		
		assertEquals("Software Design", r.getTitle());
		assertEquals(0, r.getMarks()); 
		assertTrue(r.getInstructor() != null 
				&& r.getInstructor() == Jackson /* reference aliasing */
				/* Q. Can you visualize this chain of method calls? */
				&& r.getInstructor().getName().equals("Jackson") 
				&& r.getInstructor().getPhoneExtension() == 70130
				&& r.getInstructor().getEmail().equals("Jackson@eecs.yorku.ca"));
		assertTrue(r.getGradeReport().length == 2);
		assertEquals(r.getGradeReport()[0], "F");
		assertEquals(r.getGradeReport()[1], "Failing");
		/* When the instructor is assigned for the course, its information displays:
		 * 	- Title of course
		 * 	- Name of instructor
		 * 	- Numerical marks, the corresponding letter grade, and its qualitative description.
		 * 
		 *  Note. Here we only test one case of mapping from marks to its grade and description.
		 *        You should test other cases (see the mapping table in the instructions PDF).
		 */
		assertEquals("Software Design, taught by Jackson, is completed with raw marks 0 (F ; Failing)", r.getInformation());
		
		r.setMarks(61);
		
		assertEquals(61, r.getMarks());
		assertTrue(r.getGradeReport().length == 2 
				&& r.getGradeReport()[0].equals("C") 
				&& r.getGradeReport()[1].equals("Competent"));
		assertEquals("Software Design, taught by Jackson, is completed with raw marks 61 (C ; Competent)", r.getInformation());
		
		Instructor jim = new Instructor("Jim Davies", 70139, "jim@yorku.ca");
		r.setInstructor(jim);
		
		/* Q. Can you visualize why this assertion is true? */
		assertTrue(r.getInstructor() != null 
				&& r.getInstructor() != Jackson 
				&& r.getInstructor() == jim);
		assertEquals("Software Design, taught by Jim Davies, is completed with raw marks 61 (C ; Competent)", r.getInformation());
	}
	
	/*
	 * Recommended exercises: 
	 * 	Visualizing and tracing (on both debugger and paper) on how objects are created and manipulated
	 * 	in each test would be extremely valuable for reinforcing your understanding.
	 */ 
	@Test
	public void test_02b() {
		/* Second argument of the constructor call is an anonymous object: 
		 * 	new Instructor("J. Gibbons", 76283, "jeremy@yorku.ca") 
		 */
		Registration r = new Registration("Data Structures", new Instructor("J. Gibbons", 76283, "jeremy@yorku.ca"));
		r.setMarks(73);
		
		assertEquals("Data Structures", r.getTitle());
		assertEquals(73, r.getMarks());
		assertTrue(r.getInstructor().getName().equals("J. Gibbons")
				&& r.getInstructor().getPhoneExtension() == 76283
				&& r.getInstructor().getEmail().equals("jeremy@yorku.ca")
				&& r.getInstructor().getInformation().equals("Instructor J. Gibbons has campus phone extension 76283 and contact email jeremy@yorku.ca"));
		assertTrue(r.getGradeReport().length == 2 
				&& r.getGradeReport()[0].equals("B") 
				&& r.getGradeReport()[1].equals("Good"));
		assertEquals("Data Structures, taught by J. Gibbons, is completed with raw marks 73 (B ; Good)", r.getInformation());
	}
	
	/*
	 * Recommended exercises: 
	 * 	Visualizing and tracing (on both debugger and paper) on how objects are created and manipulated
	 * 	in each test would be extremely valuable for reinforcing your understanding.
	 */ 
	@Test
	public void test03a() {
		Instructor alan = new Instructor("Dr. Pepper", 70130, "Jackson@eecs.yorku.ca");
		Instructor mark = new Instructor("Dr. Darwin", 70139, "jonathan@yorku.ca");
		 
		Participant suyeon = new Participant("S. Y. Lee"); 
		/*
		 * Length of the returned array from `getRegistrations` corresponds to
		 * the number of registrations added by the participant so far.
		 * See the instructions PDF regarding the max number of registrations that can be added. 
		 */
		Registration[] suyeonRegistrations = suyeon.getRegistrations();
		String report = suyeon.getGPAReport();
		
		/* empty list of registrations to begin with */
		assertTrue(suyeonRegistrations.length == 0); 
		/* GPA undefined over an empty list of registrations */
		assertEquals("No GPA available yet for S. Y. Lee", report);
		/* non-registered courses have default marks -1 */
		assertTrue(suyeon.marksOf("Intro. to OOP") == -1);
		assertTrue(suyeon.marksOf("Heavy Metal Music") == -1);
		assertTrue(suyeon.marksOf("Psychology I") == -1); 
		
		Registration r1 = new Registration("Intro. to OOP", alan);
		/* add two registrations for suyeon
		 * Hints: 
		 * 	- Are the two `addRegistration` calls denote the same method? 
		 *	- Or an overloaded method (i.e., methods with the same name but distinct input parameter types)?
		 */
		suyeon.addRegistration(r1);
		suyeon.addRegistration("Heavy Metal Music");
		
		assertTrue(suyeon.getRegistrations().length == 2
				&& suyeon.getRegistrations()[0] == r1
				&& suyeon.getRegistrations()[1].getTitle().equals("Heavy Metal Music")
				&& suyeon.getRegistrations()[1].getInstructor() == null);
		assertTrue(suyeon.getRegistrations()[0].getMarks() == 0);
		assertTrue(suyeon.getRegistrations()[1].getMarks() == 0);
		assertTrue(suyeon.marksOf("Intro. to OOP") == 0); /* now a registered course */
		assertTrue(suyeon.marksOf("Heavy Metal Music") == 0); /* now a registered course */
		assertTrue(suyeon.marksOf("Psychology I") == -1); /* still a non-registered course */
		
		suyeon.getRegistrations()[1].setInstructor(mark);
		
		assertTrue(suyeon.getRegistrations()[1].getInstructor() == mark);
		
		/*
		 * Notice the format of GPA report: 
		 *  - GPA value is displayed with 1 digit after the decimal point.
		 * 	- The comma-separated list of `GradePoint (LetterGrade)` is surrounded by curly braces.
		 * 	- There is a space after each comma and after the colon.
		 */
		assertEquals("S. Y. Lee's GPA of {0 (F), 0 (F)}: 0.0", suyeon.getGPAReport());
		
		suyeon.updateMarks("Intro. to OOP", 61); 
		suyeon.updateMarks("Heavy Metal Music", 79);
		/* non-existing course -> do nothing */
		suyeon.updateMarks("Psychology I", 89); 
		assertTrue(suyeon.getRegistrations()[0].getMarks() == 61); /* Grade: C; GP: 6  */
		assertTrue(suyeon.getRegistrations()[1].getMarks() == 79); /* Grade: B; GP: 7 */
		assertTrue(suyeon.marksOf("Intro. to OOP") == 61);
		assertTrue(suyeon.marksOf("Heavy Metal Music") == 79);
		assertTrue(suyeon.marksOf("Psychology I") == -1);
		/* GPA = sum of GPs divided by number of courses */
		assertEquals("S. Y. Lee's GPA of {6 (C), 7 (B)}: 6.5", suyeon.getGPAReport()); 
		
		Participant yuna = new Participant("Y. Lee");
		yuna.addRegistration(new Registration("Heavy Metal Music", mark));
		yuna.addRegistration(new Registration("Intro. to OOP", alan));
		/* Q. Can you understand the two occurrences of anonymous objects below? */
		yuna.addRegistration(new Registration(
								"Psychology I", 
								new Instructor("Tom", 70141, "tom@yorku.ca")));
		yuna.updateMarks("Heavy Metal Music", 85);
		yuna.updateMarks("Intro. to OOP", 58);
		yuna.updateMarks("Psychology I", 66);
		
		assertTrue(yuna.getRegistrations()[0].getMarks() == 85); /* Grade: A; GP: 8  */
		assertTrue(yuna.getRegistrations()[1].getMarks() == 58); /* Grade: D; GP: 5 */
		assertTrue(yuna.getRegistrations()[2].getMarks() == 66); /* Grade: C; GP: 6 */
		assertTrue(yuna.marksOf("Heavy Metal Music") == 85);
		assertTrue(yuna.marksOf("Intro. to OOP") == 58);
		assertTrue(yuna.marksOf("Psychology I") == 66);
		assertEquals("Y. Lee's GPA of {8 (A), 5 (D), 6 (C)}: 6.3", yuna.getGPAReport());
		
		/* Suyeon and Yuna are classmates of two courses,
		 * but the registration objects are distinct.
		 */
		assertEquals(suyeon.getRegistrations()[0].getTitle(), yuna.getRegistrations()[1].getTitle());
		assertTrue(suyeon.getRegistrations()[0] != yuna.getRegistrations()[1]);
		assertEquals(suyeon.getRegistrations()[1].getTitle(), yuna.getRegistrations()[0].getTitle());
		assertTrue(suyeon.getRegistrations()[1] != yuna.getRegistrations()[0]);
		
		/* At the end of the semester, clear registrations of students. */
		suyeon.clearRegistrations();
		yuna.clearRegistrations();
		
		assertTrue(suyeon.getRegistrations().length == 0
				&& yuna.getRegistrations().length == 0);
		assertTrue(suyeon.getGPAReport().equals("No GPA available yet for S. Y. Lee")
				&& yuna.getGPAReport().equals("No GPA available yet for Y. Lee"));
		String[] courses = {"Intro. to OOP", "Heavy Metal Music", "Psychology I", "Software Design"};
		/* Q. Without the loop below, 
		 * how many lines of assertions need to be written explicitly? 
		 */
		for(int i = 0; i < courses.length; i ++) {
			assertTrue(suyeon.marksOf(courses[i]) == -1);
			assertTrue(yuna.marksOf(courses[i]) == -1);
		}
		
		/* Next semester, students may choose to re-take some courses. */
		suyeon.addRegistration("Heavy Metal Music");
		suyeon.updateMarks("Heavy Metal Music", 99);
		
		assertTrue(suyeon.getRegistrations().length == 1);
		assertEquals("S. Y. Lee's GPA of {9 (A+)}: 9.0", suyeon.getGPAReport());
		assertEquals("Heavy Metal Music has not yet been assigned an instructor", suyeon.getRegistrations()[0].getInformation());
	}
	
	/*
	 * Recommended exercises: 
	 * 	Visualizing and tracing (on both debugger and paper) on how objects are created and manipulated
	 * 	in each test would be extremely valuable for reinforcing your understanding.
	 */ 
	@Test
	public void test03b() {
		Participant heeyeon = new Participant("H. Y. Kang");
		Registration r1 = new Registration("EECS2001");
		Registration r2 = new Registration("EECS2011");
		Registration r3 = new Registration("EECS2021");
		Registration r4 = new Registration("EECS2031");
		Registration r5 = new Registration("EECS1090");
		Registration[] list = {r1, r2, r3, r4, r5};
		
		/* Q. Without the loop below, 
		 * how many lines of assertions need to be written explicitly? 
		 */
		for(int i = 0; i < list.length; i ++) {
			heeyeon.addRegistration(list[i]);
			assertTrue(heeyeon.getRegistrations().length == i + 1);
			assertTrue(heeyeon.getRegistrations()[i] == list[i]);
		}
		
		/* Maximum number of registrations allowed is 5.
		 * Adding beyond the maximum capacity will have no effect. 
		 */
		heeyeon.addRegistration(new Registration("ECON1000"));
		heeyeon.addRegistration(new Registration("ECON1010"));
		
		assertTrue(heeyeon.getRegistrations().length == 5);
		assertTrue(heeyeon.getRegistrations()[0] == r1);
		assertTrue(heeyeon.getRegistrations()[1] == r2);
		assertTrue(heeyeon.getRegistrations()[2] == r3);
		assertTrue(heeyeon.getRegistrations()[3] == r4);
		assertTrue(heeyeon.getRegistrations()[4] == r5);
	}
	
	/*
	 * Recommended exercises: 
	 * 	Visualizing and tracing (on both debugger and paper) on how objects are created and manipulated
	 * 	in each test would be extremely valuable for reinforcing your understanding.
	 */ 
	@Test
	public void test_04() {
		OnlineSchool school = new OnlineSchool();
		/* courses not existing (yet) */
		Participant[] list1 = school.getParticipants("Intro. to OOP");
		Participant[] list2 = school.getParticipants("Heavy Metal Music");
		Participant[] list3 = school.getParticipants("Chamber Music");
		
		assertTrue(list1.length == 0 && list2.length == 0 && list3.length == 0);
		
		Participant alan = new Participant("Dr. Pepper");
		Participant mark = new Participant("Dr. Darwin");
		Participant tom = new Participant("Nigyni Jr."); 
		school.addParticipant(alan);
		school.addParticipant(mark);
		school.addParticipant(tom);
		tom.addRegistration("Heavy Metal Music");
		tom.addRegistration("Chamber Music");
		tom.addRegistration("Intro. to OOP");
		alan.addRegistration("Intro. to OOP");
		mark.addRegistration("Heavy Metal Music");
		mark.addRegistration("Intro. to OOP");
		
		/* Order in which participants appear in the returned array of `getParticipants` should be
		 * the same as how they appear in the school's list of participants (e.g., alan, mark, tom). 
		 */
		list1 = school.getParticipants("Intro. to OOP");
		list2 = school.getParticipants("Heavy Metal Music");
		list3 = school.getParticipants("Chamber Music");
		
		assertTrue(list1.length == 3
				&& list1[0] == alan
				&& list1[1] == mark
				&& list1[2] == tom);
		assertTrue(list2.length == 2
				&& list2[0] == mark
				&& list2[1] == tom);
		assertTrue(list3.length == 1
				&& list3[0] == tom);
		
		/* non-existing course */
		assertTrue(school.getParticipants("How to Make Fish and Chips").length == 0);
	}
	
	/* You may want to write a test similar to test03b: 
	 * an online school can be added up to 100 participants. 
	 */
}