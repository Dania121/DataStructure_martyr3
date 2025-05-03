package application;

import java.util.Date;

public class Hash {
	private HashNode[] hashArray = new HashNode[11];
	private int size = 11;
	private int counter = 0;
	private int capacity;
	// private int size;

	public Hash() {
		initialization();
	}

	public int getSize() {
		return size;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public HashNode getHashArray(int index) {
		return hashArray[index];
	}

	public void insert(String date, Martyr martyr) {
		int i = search(date);
		if (i == -1) {
			int j = hash(date);
			hashArray[j] = new HashNode(date);
			hashArray[j].setFlag('F');
			if (martyr != null) {
				// hashArray[j].setCounter(hashArray[j].getCounter()+1);
				hashArray[j].getAvlTree().insertMartyr(martyr);
			}
			counter++;
			if (counter >= size / 2)
				rehash(getNextPrime(2 * size));
		} else {
			hashArray[i].getAvlTree().insertMartyr(martyr);
		}
	}

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

	private void rehash(int newSize) {
		HashNode[] h = hashArray;
		hashArray = new HashNode[newSize];
		initialization();
		size = newSize;
		for (int i = 0; i < h.length; i++) {
			if (h[i].getFlag() == 'F') {
				int index = hash(h[i].getData());
				hashArray[index] = h[i];
			}
		}

	}

	public String delete(String data) {
		int j = search(data);
		if (j != -1) {
			counter--;
			hashArray[j].setFlag('D');
			if (counter <= size / 4)
				rehash(getPrevPrime(size / 2));

			return data;
		} else
			return null;
	}

	private int getPrevPrime(int x) {
		while (true) {
			if (isPrime(x))
				break;
			if (x < 3) {
				x = 3;
				break;
			}
			x--;
		}

		return x;
	}

	public int search(String data) {

		int i = hash(data);

		if (hashArray[i].isFull())
			return i;
		return -1;
	}

//	public int search(String data) {
//
//		int h = Math.abs(data.hashCode());
//		int j = 1;
//		int i = h % hashArray.length;
//		int index = -1;
//
//		while (hashArray[i].isFull()) {
//			if (hashArray[i].getData().equals(data)) { // Compare strings using equals() method
//				index = i;
//				break;
//			}
//			i = (h % size + (int) Math.pow(j, 2)) % hashArray.length;
//			j++;
//		}
//
//		return index;
//	}

	public void update(String oldDate, String newDate) {

		int i = search(newDate);
		int index = search(oldDate);
		if (i == -1) {
			delete(oldDate);
			hashArray[index].setData(newDate);
			;
			int newIndex = hash(newDate);
			hashArray[newIndex] = hashArray[index];
			hashArray[newIndex].setFlag('F');
			hashArray[index] = new HashNode();

		} else if (i != index) {
			insert(newDate, hashArray[i].getAvlTree().getRoot().getMartyr());
			hashArray[i].setFlag('D');
			hashArray[index].setData(newDate);

		}

	}

	private void initialization() {
		for (int i = 0; i < hashArray.length; i++) {
			hashArray[i] = new HashNode();
		}
	}

	private int hash(String data) {
		int h = Math.abs(data.hashCode());
		int j = 1;
		int i = h % hashArray.length; // Ensure positive index using bitwise AND with 0x7FFFFFFF
		while (hashArray[i].isFull() && !hashArray[i].getData().equals(data)) {
			i = (h + (int) Math.pow(j, 2)) % hashArray.length; // Apply modulo operation to ensure the index stays
																// within bounds
			j++;
		}
		return i;
	}

//	private int hash(String data) {
//		int h = data.hashCode(), j = 1, i = h % hashArray.length;
//		while (hashArray[i].isFull()) {
//			i = (h + (int) Math.pow(j, 2)) % hashArray.length;
//			j++;
//		}
//		return i;
//	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (HashNode Node : hashArray) {
			if (Node.getFlag() != 'D')
				s.append(Node).append("\n");
			else
				s.append("null\n");
		}
		return s.toString();
	}

	public void printHashTable() {
		for (int i = 0; i < hashArray.length; i++) {
			HashNode node = hashArray[i];
			System.out.print("Index " + i + ": ");
			if (node.getFlag() != 'D') {
				System.out.println(node.getData());

				// Check if the AVL tree is not empty
				if (!node.getAvlTree().isEmpty()) {
					System.out.println("AVL Tree contents:");
					node.getAvlTree().printAvl(node.getAvlTree().getRoot());
				} else {
					System.out.println("AVL Tree is empty");
				}
			} else {
				System.out.println("null");
			}
		}
	}

}
