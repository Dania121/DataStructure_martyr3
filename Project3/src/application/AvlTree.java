package application;

import java.util.Date;

public class AvlTree {
	private AvlNode root;

	public AvlTree() {
		super();
	}

	public AvlTree(AvlNode root) {
		super();
		this.root = root;
	}

	public AvlNode getRoot() {
		return root;
	}

	public void setRoot(AvlNode root) {
		this.root = root;
	}

	public int height() {
		return height(root);
	}

	private int height(AvlNode node) {
		if (node == null) {
			return 0;
		} else {
			int leftHeight = height(node.getLeft());
			int rightHeight = height(node.getRight());

			// Height of the current node is the height of the tallest subtree + 1
			return Math.max(leftHeight, rightHeight) + 1;
		}
	}

	private void updateHeight(AvlNode node) {
		if (node != null) {
			int maxHeight = Math.max(height(node.getLeft()), height(node.getRight()));
			node.setHeight(maxHeight + 1);
		}
	}

	private int balance(AvlNode node) {
		if (node != null) {
			return height(node.getLeft()) - height(node.getRight());
		}
		return 0;
	}

	private AvlNode rightRotate(AvlNode node) {
		AvlNode leftNode = node.getLeft();
		AvlNode centerNode = leftNode.getRight();
		leftNode.setRight(node);
		node.setLeft(centerNode);
		updateHeight(node);
		updateHeight(leftNode);
		return leftNode;
	}

	private AvlNode leftRotate(AvlNode node) {
		AvlNode rightNode = node.getRight();
		AvlNode centerNode = rightNode.getLeft();
		rightNode.setLeft(node);
		node.setRight(centerNode);
		updateHeight(node);
		updateHeight(rightNode);
		return rightNode;
	}

	private AvlNode rightLeftRotate(AvlNode node) {
		node.setRight(rightRotate(node.getRight()));
		return leftRotate(node);
	}

	private AvlNode leftRightRotate(AvlNode node) {
		node.setLeft(leftRotate(node.getLeft()));
		return rightRotate(node);
	}

	private AvlNode applyRotation(AvlNode node) {
		int balance = balance(node);
		if (balance > 1) {
			if (balance(node.getLeft()) < 0) {

				return leftRightRotate(node);
			} else {

				return rightRotate(node);
			}
		} else if (balance < -1) {
			if (balance(node.getRight()) > 0) {

				return rightLeftRotate(node);
			} else {

				return leftRotate(node);
			}
		}
		return node;
	}

	public void insertMartyr(Martyr martyr) {
		if (!contains(martyr.getName(), root)) {
			root = insertRecursive(root, martyr);
		} else {
			System.out.println("Martyr already exists: " + martyr.getName());
		}
	}

	public boolean contains(String name, AvlNode root) {
		if (root == null) {
			return false; // District tree is empty
		}

		int comparisonResult = name.compareToIgnoreCase(root.getMartyr().getName());
		if (comparisonResult == 0) {
			return true; // District found
		} else if (comparisonResult < 0) {
			return contains(name, root.getLeft());
		} else {
			return contains(name, root.getRight());
		}
	}

	private AvlNode insertRecursive(AvlNode node, Martyr martyr) {
		// If the current node is null, create a new node with the martyr object
		if (node == null) {
			return new AvlNode(martyr);
		}

		// Compare the district of the martyr with the district in the current node
		int districtComparison = martyr.getDistrict().compareToIgnoreCase(node.getMartyr().getDistrict());

		// If the districts are the same, compare the names
		if (districtComparison == 0) {
			// Compare the name of the martyr with the name in the current node
			int nameComparison = martyr.getName().compareToIgnoreCase(node.getMartyr().getName());

			// If the martyr name is less than the name in the current node, insert in the
			// left subtree
			if (nameComparison < 0) {
				node.setLeft(insertRecursive(node.getLeft(), martyr));
			}
			// If the martyr name is greater than the name in the current node, insert in
			// the right subtree
			else if (nameComparison > 0) {
				node.setRight(insertRecursive(node.getRight(), martyr));
			}
			// If the martyr name is equal to the name in the current node, do not insert
			// (duplicate)
			// You can handle duplicates according to your requirements
		}
		// If the districts are different, insert based on district comparison
		else if (districtComparison < 0) {
			node.setLeft(insertRecursive(node.getLeft(), martyr));
		} else {
			node.setRight(insertRecursive(node.getRight(), martyr));
		}

		// Update the height of the current node
		updateHeight(node);

		// Perform rotation if necessary to balance the tree
		return applyRotation(node);
	}

//	private AvlNode applyRotation(AvlNode node) {
//		int balance = balance(node);
//		if (balance > 1) {
//			if (balance(node.getLeft()) < 0) {
//				node.setLeft(leftRotate(node.getLeft()));
//			}
//			return rightRotate(node);
//		}
//		if (balance < -1) {
//			if (balance(node.getRight()) > 0) {
//				node.setRight(rightRotate(node.getRight()));
//			}
//			return leftRotate(node);
//		}
//		return node;
//	}

	public void printAvl(AvlNode root) {
		if (root != null) {
			printAvl(root.getLeft());

			System.out.println(root.getMartyr());
			// root.getLocaionTree().printLocationTree(root.getLocaionTree().getRoot());

			// System.out.println("___________________________________________________");
			printAvl(root.getRight());

		}
	}

	public int getSize() {
		return getSize(root);
	}

	private int getSize(AvlNode node) {
		if (node == null) {
			return 0;
		}
		// Recursively count the nodes in the left and right subtrees
		int leftSize = getSize(node.getLeft());
		int rightSize = getSize(node.getRight());
		// Add 1 to include the current node
		return leftSize + rightSize + 1;
	}

	public boolean isEmpty() {
		return root == null;

	}

	public AvlNode deleteNode(AvlNode root, Martyr key) {
		if (root == null)
			return root;

		if (((Martyr) key).compareTo((Martyr) root.getMartyr()) < 0)
			root.setLeft(deleteNode(root.getLeft(), key));

		else if (((Martyr) key).compareTo((Martyr) root.getMartyr()) > 0)
			root.setRight(deleteNode(root.getRight(), key));

		else {

			if ((root.getLeft() == null) || (root.getRight() == null)) {
				AvlNode temp = null;
				if (temp == root.getLeft())
					temp = root.getRight();
				else
					temp = root.getLeft();

				if (temp == null) {
					temp = root;
					root = null;
				} else
					root = temp;
			} else {
				AvlNode temp = minValueNode(root.getRight());
				root.setMartyr(temp.getMartyr());
				root.setRight(deleteNode(root.getRight(), temp.getMartyr()));
			}
		}
		if (root == null)
			return root;

		root.setHeight(Math.max(height(root.getLeft()), height(root.getRight())) + 1);
		return applyRotation(root);
	}

//	public AvlNode delete(Martyr martyr, AvlNode node) {
//
//		if (node == null) {
//			return null; // Martyr not found in the tree
//		}
//
//		
//		int nameComparison = martyr.getName().compareToIgnoreCase(node.getMartyr().getName());
//		int districtComparison = martyr.getDistrict().compareToIgnoreCase(node.getMartyr().getDistrict());
//
//		
//		if (nameComparison < 0) {
//			node.setLeft(delete(martyr, node.getLeft()));
//		} else if (nameComparison > 0) {
//			
//			node.setRight(delete(martyr, node.getRight()));
//		} else {
//			
//			if (node.getLeft() == null) {
//				return node.getRight(); 
//			} else if (node.getRight() == null) {
//				return node.getLeft(); 
//			} else {
//				
//				AvlNode successor = minValueNode(node.getRight());
//
//				
//				node.setMartyr(successor.getMartyr());
//
//				
//				node.setRight(delete(successor.getMartyr(), node.getRight()));
//			}
//		}
//
//		
//		updateHeight(node);
//
//		return applyRotation(node);
//	}

	// Helper method to find the minimum value node in a subtree
	private AvlNode minValueNode(AvlNode node) {
		AvlNode current = node;
		while (current.getLeft() != null) {
			current = current.getLeft();
		}
		return current;
	}

	public String getMaxDistrict() {
		if (root == null)
			return "No districts exist";
		Queue q = new Queue();
		q.inQueue(root);
		int mx = 0;
		String district = "";
		DistrictHash hash = new DistrictHash();
		while (!q.isEmpty()) {
			AvlNode node = (AvlNode) q.deQueue();
			if (node.getRight() != null)
				q.inQueue(node.getRight());
			if (node.getLeft() != null)
				q.inQueue(node.getLeft());
			int size = hash.insert(node.getMartyr().getDistrict());
			if (size > mx) {
				mx = size;
				district = node.getMartyr().getDistrict();
			}
		}
		return district;
	}

	public String getMaxLocation() {
		if (root == null)
			return "No locations exist";
		Queue q = new Queue();
		q.inQueue(root);
		int mx = 0;
		String location = "";
		DistrictHash hash = new DistrictHash();
		while (!q.isEmpty()) {
			AvlNode node = (AvlNode) q.deQueue();
			if (node.getRight() != null)
				q.inQueue(node.getRight());
			if (node.getLeft() != null)
				q.inQueue(node.getLeft());
			int size = hash.insert(node.getMartyr().getLocation());
			if (size > mx) {
				mx = size;
				location = node.getMartyr().getLocation();
			}

		}
		return location;

	}

}
