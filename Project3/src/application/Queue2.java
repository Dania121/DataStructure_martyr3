package application;

public class Queue2 {
	Node2 front;
	Node2 rear;
	LinkedList2 list;

	public Queue2() {
		this.list = new LinkedList2();
	}

	public Node2 getFront() {
		return front;
	}

	public void setFront(Node2 front) {
		this.front = front;
	}

	public Node2 getRear() {
		return rear;
	}

	public void setRear(Node2 rear) {
		this.rear = rear;
	}

	public LinkedList2 getList() {
		return list;
	}

	public void setList(LinkedList2 list) {
		this.list = list;
	}

	public void inQueue(AvlNode data) {
		list.addLast(data);
		front = list.head;
		rear = list.tail;
	}

	public Node2 deQueue() {
		if (isEmpty()) {
			System.out.println("Queue is empty");
			return null;
		} else {
			Node2 result = list.deleteFirst();
			front = list.head;
			return result;
		}
	}

	public boolean isEmpty() {
		return front == null;
	}

	public void printQueue() {
		if (isEmpty()) {
			System.out.println("Queue is empty");
		} else {
			System.out.println("Queue contents:");
			Node2 current = front;
			while (current != null) {
				System.out.println(current.data);
				current = current.next;
			}
		}
	}
}
