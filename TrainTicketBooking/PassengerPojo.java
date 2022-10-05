package TrainTicketBooking;

public class PassengerPojo {
	private static int id=1;
	private String name;
	private int age;
	private String gender;
	private String child_name;
	private int child_age;
	private String child_gender;
	private String berth_pref;
	int passengerId=id++;
	String alloted;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age=age;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender=gender;
	}
	
	public String getChild_name() {
		return child_name;
	}
	public void setChild_name(String cname) {
		this.child_name=cname;
	}
	
	public int getChild_age() {
		return child_age;
	}
	public void setChild_age(int child_age) {
		this.child_age=child_age;
	}
	
	public String getChild_gender() {
		return child_gender;
	}
	public void setChild_gender(String child_gender) {
		this.child_gender=child_gender;
	}
	
	public String getBerth_pref() {
		return berth_pref;
	}
	public void setBerth_pref(String berth_pref) {
		this.berth_pref=berth_pref;
	}
	
	public PassengerPojo() {
		alloted=" ";
	}
}
