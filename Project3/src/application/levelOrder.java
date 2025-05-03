package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class levelOrder extends Application {
	ObservableList<Martyr> data;
	TableView<Martyr> tv;
	private HashNode hNode;

	public levelOrder() {
		// super();

		hNode = new HashNode();
		data = FXCollections.observableArrayList();
	}

	public HashNode gethNode() {
		return hNode;
	}

	public void sethNode(HashNode hNode) {
		this.hNode = hNode;
		levelOrder(hNode.getAvlTree().getRoot());

	}

	@Override
	public void start(Stage arg0) throws Exception {

		tv = new TableView<>();
		// data = FXCollections.observableArrayList();
		TableColumn<Martyr, String> nameColumn = new TableColumn<>("Name");
		TableColumn<Martyr, Integer> ageColumn = new TableColumn<>("Age");
		TableColumn<Martyr, String> districtColumn = new TableColumn<>("District");
		TableColumn<Martyr, String> locationColumn = new TableColumn<>("Location");
		TableColumn<Martyr, String> genderColumn = new TableColumn<>("Gender");
		tv.getColumns().addAll(nameColumn, ageColumn, districtColumn, locationColumn, genderColumn);

		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
		districtColumn.setCellValueFactory(new PropertyValueFactory<>("District"));
		locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
		genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
		nameColumn.setPrefWidth(275);
//		    tableView.setPrefHeight(100); // Set preferred height
//		    tableView.setPrefWidth(500); 

		tv.setItems(data);

		BorderPane pane = new BorderPane();
		pane.setCenter(tv);
		Scene scene = new Scene(pane, 600, 600);
		arg0.setScene(scene);
		arg0.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

//	public void levelOrder(AvlNode root) {
//		if (root == null)
//			return;
//
//		Queue q = new Queue();
//		q.inQueue(root);
//		while (!q.isEmpty()) {
//			AvlNode current = (AvlNode) q.getFront();
//
//			// Collect data of the current node
//			Martyr martyr = new Martyr(current.getMartyr().getName(), current.getMartyr().getAge(),
//					current.getMartyr().getDistrict(), current.getMartyr().getLocation(),
//					current.getMartyr().getGender());
//			data.add(martyr);
//
//			if (current.getRight() != null) {
//				q.inQueue(current.getRight());
//			}
//			if (current.getLeft() != null) {
//				q.inQueue(current.getLeft());
//			}
//			q.deQueue();
//		}
//	}
	public void levelOrder(AvlNode root) {
		if (root == null)
			return;

		Queue2 q = new Queue2();
		q.inQueue(root);
		while (!q.isEmpty()) {
			Node2 currentNode = q.deQueue(); // Get the front node from the queue

//			if (currentNode instanceof AvlNode) { // Check if it's an AvlNode
//				AvlNode current = (AvlNode) currentNode; // Cast it to AvlNode

			// Collect data of the current node
			Martyr martyr = new Martyr(currentNode.data.getMartyr().getName(), currentNode.data.getMartyr().getAge(),
					currentNode.data.getMartyr().getDistrict(), currentNode.data.getMartyr().getLocation(),
					currentNode.data.getMartyr().getGender());
			data.add(martyr);

			if (currentNode.data.getRight() != null) {
				q.inQueue(currentNode.data.getRight());
			}
			if (currentNode.data.getLeft() != null) {
				q.inQueue(currentNode.data.getLeft());
			}
		}
	}
}
