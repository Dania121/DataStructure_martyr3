package application;

public class DistrictHash {
	private DhashNode[] dhash = new DhashNode[11];
	private int size;
	private int counter;

	public DistrictHash() {
		super();
		initialization();
	}

	public DhashNode[] getDhash() {
		return dhash;
	}

	public void setDhash(DhashNode[] dhash) {
		this.dhash = dhash;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}
	
	
	public int insert(String date) {
	//	int i = search(date);
		//if (i == -1) {
			int j = hash(date);
			if(dhash[j].getCounter()==0) dhash[j] = new DhashNode(date);
			dhash[j].setCounter(dhash[j].getCounter()+1);
			int x = dhash[j].getCounter();
			counter++;
			if (counter >= size / 2)
				rehash(getNextPrime(2 * size));
		//} else {
		//	hashArray[i].getAvlTree().insertMartyr(martyr);
	//	}
			return x;
	}
	
	private void rehash(int newSize) {
		DhashNode[] h = dhash;
		dhash = new DhashNode[newSize];
		initialization();
		size = newSize;
		for (int i = 0; i < h.length; i++) {
			if (h[i].getCounter() != 0) {
				int index = hash(h[i].getDistrict());
				dhash[index] = h[i];
			}
		}

	}
	
	
	
	
	private void initialization() {
		for (int i = 0; i < dhash.length; i++) {
			dhash[i] = new DhashNode();
		}
	}
	
	
	
	
	private int hash(String data) {
		int h = Math.abs(data.hashCode());
		int j = 1;
		int i = h % dhash.length; // Ensure positive index using bitwise AND with 0x7FFFFFFF
		while (dhash[i].getCounter()!=0 && !dhash[i].getDistrict().equals(data)) {
			i = Math.abs((h + (int) Math.pow(j, 2)) % dhash.length); // Apply modulo operation to ensure the index stays
																// within bounds
			j++;
		}
		return i;
	}
	//dhash[i].isFull() && 

	private int getNextPrime(int x) {
		while (true) {
			if (isPrime(x))
				break;
			x++;
		}
		return x;
	}
	
	private boolean isPrime(int n) {
		if (n <= 1)
			return false;

		for (int i = 2; i < n; i++)
			if (n % i == 0)
				return false;

		return true;
	}

	
}
