package application;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class heapSort extends Application {
	ObservableList<Martyr> data;
	TableView<Martyr> tv;
	private HashNode hNode;
	Martyr[] list = new Martyr[10];
	int size = 0, capacity=10;

	public heapSort() {
		hNode = new HashNode();
		data = FXCollections.observableArrayList();

	}

	public HashNode gethNode() {
		return hNode;
	}

	public void sethNode(HashNode hNode) {
		this.hNode = hNode;
		fillArray(hNode.getAvlTree().getRoot());
		heapSort(list);
		for(int i=1; i<=size; i++) {
			data.add(list[i]);
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		tv = new TableView<>();
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
		nameColumn.setPrefWidth(190);
//		    tableView.setPrefHeight(100); // Set preferred height
//		    tableView.setPrefWidth(500); 

		tv.setItems(data);

		BorderPane pane = new BorderPane();
		pane.setCenter(tv);
		Scene scene = new Scene(pane, 515, 600);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void heapSort(Martyr arr[]) {
		int N = size+1;
		buildHeap(arr, N);
		for (int i = N - 1; i > 0; i--) {
			Martyr temp = arr[1];
			arr[1] = arr[i];
			arr[i] = temp;
			heapify(arr, i, 1);
		}
	}

	public void buildHeap(Martyr arr[], int N) {
		// Index of last non-leaf node
		int startIdx = (N / 2);
		for (int i = startIdx; i > 0; i--) {
			heapify(arr, N, i);
		}
	}

	public void heapify(Martyr arr[], int N, int i) {
		int largest = i;
		int l = 2 * i;
		int r = 2 * i + 1;
		// If left child is larger than root
		try {
		if (l < N && arr[l].getAge() > arr[largest].getAge())
			largest = l;
		// If right child is larger than largest so far
		if (r < N && arr[r].getAge() > arr[largest].getAge())
			largest = r;
		// If largest is not root
		if (largest != i) {
			Martyr swap = arr[i];
			arr[i] = arr[largest];
			arr[largest] = swap;
			// Recursively heapify the affected sub-tree
			heapify(arr, N, largest);
		}
		} catch(NullPointerException ex) { System.out.println(l);}
	}

	public void fillArray(AvlNode node) {
		if (node == null)
			return;
		fillArray(node.getLeft());
		if (size==capacity-1) resize();
		list[++size] = node.getMartyr();
		fillArray(node.getRight());
	}
	private void resize() {
		Martyr[] temp = list;
		list = new Martyr[capacity*2];
		capacity*=2;
		for(int i=0; i<=size; i++) {
			list[i] = temp[i];
		}
	}

}
