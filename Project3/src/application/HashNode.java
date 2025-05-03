package application;

import java.util.Date;

public class HashNode {
	private String data;
	private char flag = 'E';
	private AvlTree avlTree;

	public HashNode() {
		avlTree = new AvlTree();
		flag = 'E';
	}

	public HashNode(String data) {
		this.data = data;
		avlTree = new AvlTree();
		flag = 'E';
	}

	public char getFlag() {
		return flag;
	}

	public void setFlag(char flag) {
		if (flag == 'D' || flag == 'E' || flag == 'F')
			this.flag = flag;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public boolean isFull() {
		return flag != 'D' && flag != 'E';
	}

	public AvlTree getAvlTree() {
		return avlTree;
	}

	public void setAvlTree(AvlTree avlTree) {
		this.avlTree = avlTree;
	}

	@Override
	public String toString() {
		return data + "";
	}

	public boolean isEmpty() {
		return data == null || flag == 'E';
	}
}
