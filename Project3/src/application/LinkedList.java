package application;

public class LinkedList {
	Node head, tail;
	int count;

	public LinkedList() {
		super();
	}

	public LinkedList(Node head, Node first, int count) {
		super();
		this.head = null;
		this.tail = null;
		this.count = 0;
	}

	public void addLast(Object data) {
	Node newNode = new Node(data);
		if (count == 0) {
			head = tail = newNode;
			count++;
		} else {
			tail.next = newNode;
			tail = newNode;
			count++;
		}
	}

	public Object deleteFirst() {
		Node result = null;
		if (count == 0) {
			return null;
		} else {
			result = head;
			head = head.next;
			count--;
			return result.data;
		}
	}
}