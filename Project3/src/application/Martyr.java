package application;

public class Martyr {
	private String name;
	private int age;
	private String location;
	private String District;
	private String gender;

	public Martyr() {
		super();
	}

	public Martyr(String name, int age, String location, String district, String gender) {
		super();
		this.name = name;
		this.age = age;
		this.location = location;
		District = district;
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDistrict() {
		return District;
	}

	public void setDistrict(String district) {
		District = district;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Martyr [name=" + name + ", age=" + age + ", location=" + location + ", District=" + District
				+ ", gender=" + gender + "]";
	}
	
	public int compareTo(Martyr m) {
		int c = this.getDistrict().compareToIgnoreCase(m.getDistrict());
		if(c==0) return this.getName().compareToIgnoreCase(m.getName());
		return c;
	}

}
