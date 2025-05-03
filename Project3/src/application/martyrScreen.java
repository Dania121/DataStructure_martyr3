package application;

import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.concurrent.atomic.AtomicReference;

public class martyrScreen extends Application {
	private HashNode hNode;
	ObservableList<Martyr> data;
	TableView<Martyr> tv;
	private ComboBox<String> disCmb;
	TextField locCmb;
	private Text sizeTxt, heightTxt;

	public ComboBox<String> getDisCmb() {
		return disCmb;
	}

	public void setDisCmb(ComboBox<String> disCmb) {
		this.disCmb = disCmb;
	}

	public martyrScreen() {
		disCmb = new ComboBox<String>(); // Initialize the ComboBox object
		hNode = new HashNode();
	}

	public void setDistrictItems(ObservableList<String> items) {
		disCmb.setItems(items);
	}

	public HashNode gethNode() {
		return hNode;
	}

	public void sethNode(HashNode hNode) {
		this.hNode = hNode;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Label name = new Label("Martyr Name");
		TextField nameTxt = new TextField();
		VBox vbox1 = new VBox();
		vbox1.getChildren().addAll(name, nameTxt);
		vbox1.setSpacing(10);
		Label age = new Label("Martyr Age");
		TextField ageTxt = new TextField();
		VBox vbox2 = new VBox();
		vbox2.getChildren().addAll(age, ageTxt);
		vbox2.setSpacing(10);
		Label dis = new Label("Martyr District");
		disCmb = new ComboBox<String>();
		disCmb.setPromptText("Choose District");
		VBox vbox3 = new VBox();
		vbox3.getChildren().addAll(dis, disCmb);
		vbox3.setSpacing(10);
		Label loc = new Label("Martyr Location");
		locCmb = new TextField();
		locCmb.setPromptText("Choose Location");
		VBox vbox4 = new VBox();
		vbox4.getChildren().addAll(loc, locCmb);
		vbox4.setSpacing(10);
		Label gender = new Label("Gender");
		RadioButton M = new RadioButton("M");
		RadioButton F = new RadioButton("F");

		ToggleGroup toggleGroup = new ToggleGroup();
		M.setToggleGroup(toggleGroup);
		F.setToggleGroup(toggleGroup);
//        M.setOnAction(e -> {
//            if (M.isSelected()) {
//                gender = "M";
//                System.out.println("Selected gender: " + gender);
//            }
//        });
//        
//        F.setOnAction(e -> {
//            if (F.isSelected()) {
//                gender = "F";
//                System.out.println("Selected gender: " + gender);
//            }
//        });

		HBox hbox = new HBox();
		hbox.getChildren().addAll(M, F);
		hbox.setSpacing(20);
		VBox vbox5 = new VBox();
		vbox5.getChildren().addAll(gender, hbox);
		vbox5.setSpacing(20);
		Button insert = new Button("insert");
		Button update = new Button("update");
		Button delete = new Button("delete");
		HBox hbox1 = new HBox();
		hbox1.getChildren().addAll(insert, update, delete);
		hbox1.setSpacing(30);
		VBox vbox6 = new VBox();
		vbox6.getChildren().addAll(vbox1, vbox2, vbox3, vbox4, vbox5, hbox1);
		vbox6.setSpacing(40);
		BorderPane pane = new BorderPane();

		tv = new TableView<>();
		data = FXCollections.observableArrayList();
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
		Label size = new Label("The tree size is :");
		sizeTxt = new Text();
		// System.out.println(hNode);
		sizeTxt.setText(String.valueOf(hNode.getAvlTree().getSize()));
		HBox hbox3 = new HBox();
		Button heap = new Button("show martyrs in heap sort by age");
		hbox3.getChildren().addAll(size, sizeTxt, heap);
		hbox3.setSpacing(20);

		Label height = new Label("The tree height is :");
		heightTxt = new Text();
		// AvlNode node = hNode.getAvlTree().getRoot();
		heightTxt.setText(String.valueOf(hNode.getAvlTree().height()));
		Button level = new Button("show martyrs level by level from right to left ");
		HBox hbox4 = new HBox();
		hbox4.getChildren().addAll(height, heightTxt, level);
		hbox4.setSpacing(20);
		VBox vbox9 = new VBox();
		vbox9.getChildren().addAll(hbox3, hbox4, tv);
		vbox9.setSpacing(40);
		pane.setLeft(vbox6);
		pane.setRight(vbox9);
//		AtomicReference<String> gender1 = new AtomicReference<>("");
		heap.setOnAction(e -> {

			heapSort heapMartyrs = new heapSort();
			try {
				heapMartyrs.sethNode(hNode);
				heapMartyrs.start(new Stage());

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});
		level.setOnAction(e->{
			levelOrder level22 = new levelOrder();
			try {
				
			level22.sethNode(hNode);
			level22.start(new Stage());
				
			}catch (Exception e1) {
				e1.printStackTrace();
			}
			
			
			
			
		});
		insert.setOnAction(e -> {
			String name1 = nameTxt.getText();
			String ageString = ageTxt.getText();

			// Perform the casting
			int age1;
			try {
				age1 = Integer.parseInt(ageString);
			} catch (NumberFormatException ex) {
				// Handle the case where the input is not a valid integer
				// You may want to show an error message to the user or provide a default value
				age1 = 0; // Default value or handle the error accordingly
			}
			String district = disCmb.getSelectionModel().getSelectedItem();
			String location = locCmb.getText();
			String gender2 = "";

			// M.setOnAction(ex -> {
			if (M.isSelected()) {
				gender2 = "M";
			}
			// });

			// F.setOnAction(ex -> {
			if (F.isSelected()) {
				gender2 = "F";
			}
//			});

			Martyr martyr = new Martyr(name1, age1, location, district, gender2);
			hNode.getAvlTree().insertMartyr(martyr);
			data.clear();
			fillTv(hNode.getAvlTree().getRoot());
			setHeightTxt();
			setSizeTxt();
		});
		delete.setOnAction(e -> {

			Martyr martyr = tv.getSelectionModel().getSelectedItem();
			data.remove(martyr);
			hNode.getAvlTree().deleteNode(hNode.getAvlTree().getRoot(), martyr);
			setSizeTxt();
			setHeightTxt();

		});

		update.setOnAction(e -> {
			Martyr martyr = tv.getSelectionModel().getSelectedItem();
			try {
				int newAge = Integer.parseInt(ageTxt.getText());
				if (newAge <= 0 || newAge > 130)
					throw new NumberFormatException();

				martyr.setAge(newAge);
				String name1 = nameTxt.getText();
				martyr.setName(name1);
				String district1 = disCmb.getSelectionModel().getSelectedItem();
				martyr.setDistrict(district1);
				String location1 = locCmb.getText();
				martyr.setLocation(location1);
				String gender1 = " ";
				if (M.isSelected()) {
					gender1 = "M";
				}
				if (F.isSelected()) {
					gender1 = "F";
				}
				martyr.setGender(gender1);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("succssfull");
				alert.setContentText("the selected martyr updates succssfully");
				alert.showAndWait();
				data.clear();
				fillTv(hNode.getAvlTree().getRoot());

			} catch (NumberFormatException ex) {
				Alert errorAlert = new Alert(AlertType.ERROR);
				errorAlert.setContentText("Please enter a valid age (1-130).");
				errorAlert.showAndWait();
			}

		});

		Scene scene = new Scene(pane, 750, 600);

		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public void fillTv(AvlNode node) {
		if (node == null)
			return;
		fillTv(node.getLeft());
		data.add(node.getMartyr());
		fillTv(node.getRight());
	}

	public void populateComboBox(AvlNode node, String prevDistrict) {
		if (node == null)
			return;
		fillTv(node.getLeft());
		if (prevDistrict == null || !prevDistrict.equals(node.getMartyr().getDistrict())) {
			disCmb.getItems().add(node.getMartyr().getDistrict());
		}
		fillTv(node.getRight());
	}

	public static void main(String[] args) {
		launch(args);
	}

	public Text getSizeTxt() {
		return sizeTxt;
	}

	public void setSizeTxt() {
		sizeTxt.setText(String.valueOf(hNode.getAvlTree().getSize()));
	}

	public Text getHeightTxt() {
		return heightTxt;
	}

	public void setHeightTxt() {
		heightTxt.setText(String.valueOf(hNode.getAvlTree().height()));
	}

}
