package application;

public class Queue {
	Object front;
	Object rear;
	LinkedList list;

	public Queue() {
		this.list = new LinkedList();
	}

	public Object getFront() {
		return front;
	}

	public void setFront(Node front) {
		this.front = front;
	}

	public Object getRear() {
		return rear;
	}

	public void setRear(Node rear) {
		this.rear = rear;
	}

	public LinkedList getList() {
		return list;
	}

	public void setList(LinkedList list) {
		this.list = list;
	}

	public void inQueue(Object data) {
		list.addLast(data);
		front = list.head;
		rear = list.tail;
	}

	public Object deQueue() {
		if (isEmpty()) {
			System.out.println("Queue is empty");
			return null;
		} else {
			Object result = list.deleteFirst();
			front = list.head;
			return result;
		}
	}

	public boolean isEmpty() {
		return front == null;
	}


}
