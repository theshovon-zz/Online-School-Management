package ONLINESchool;

/*
 * Each online school may accommodate up to 100 participants. 
 */
public class OnlineSchool {
	
	/*
	 * Attributes
	 */
	final int MAX_NUM_PARTICIPANTS = 100;
	Participant[] participants;
	int nop; /* number of participants */
	
	/*
	 * Constructors
	 */
	public OnlineSchool() {
		this.participants = new Participant[MAX_NUM_PARTICIPANTS];
		this.nop = 0;
	}
	
	/*
	 * Mutators
	 */
	public void addParticipant(Participant p) {
		if(this.nop < MAX_NUM_PARTICIPANTS) {
			this.participants[this.nop] = p;
			this.nop ++;
		}
	}
	
	/*
	 * Accessors
	 */
	public Participant[] getParticipants(String title) {
		Participant[] temp = new Participant[this.nop];
		int count = 0;
		for(int i = 0; i < this.nop; i ++) {
			if(this.participants[i].isRegistered(title)) {
				temp[count] = this.participants[i];
				count ++;
			}
		}
		Participant[] result = new Participant[count];
		for(int i = 0; i < count; i ++) {
			result[i] = temp[i];
		}
		return result;
	}
}
