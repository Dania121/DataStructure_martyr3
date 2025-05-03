package application;

public class LinkedList2 {
	Node2 head, tail;
	int count;

	public LinkedList2() {
		super();
	}

	public LinkedList2(Node2 head, Node2 first, int count) {
		super();
		this.head = null;
		this.tail = null;
		this.count = 0;
	}

	public void addLast(AvlNode data) {
	Node2 newNode = new Node2(data);
		if (count == 0) {
			head = tail = newNode;
			count++;
		} else {
			tail.next = newNode;
			tail = newNode;
			count++;
		}
	}

	public Node2 deleteFirst() {
		Node2 result = null;
		if (count == 0) {
			return null;
		} else {
			result = head;
			head = head.next;
			count--;
			return result;
		}
	}
}