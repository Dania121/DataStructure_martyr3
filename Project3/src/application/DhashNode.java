package application;

public class DhashNode {
	private String district;
	private int counter;

	public DhashNode() {
		super();
		district="";
		counter=0;
	}
	

	public DhashNode(String district) {
		super();
		this.district = district;
		this.counter = 0;
	}


	public DhashNode(String district, int counter) {
		super();
		this.district = district;
		this.counter = counter;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

}
