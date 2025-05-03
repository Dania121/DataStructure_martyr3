package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.collections.FXCollections;

public class Main extends Application {

	File f;
	Hash hash = new Hash();
	Text currentDate;
	Text totalTxt;
	Text DmaxTxt;
	DatePicker Dinsert;
	private int currentIndex = 0;
	ComboBox<String> tempCmb = new ComboBox<String>();
	DistrictHash dhash = new DistrictHash();
	Text LmaxTxt;
	ObservableList<String> data;
	ListView<String> tv;
	String date22;

	public int getCurrentIndex() {
		return currentIndex;
	}

	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}

	@Override
	public void start(Stage primaryStage) {
		try {

			Button file = new Button("upload");
			DatePicker date = new DatePicker();
			date.getEditor().setDisable(true);
			Text text = new Text();
			Button search = new Button("Search");
			Button save = new Button("Save");

			HBox hbox1 = new HBox();
			hbox1.getChildren().addAll(file, date, text, search, save);
			hbox1.setSpacing(70);
			Label l = new Label();
			Button up = new Button("UP");
			Button down = new Button("Down");
			currentDate = new Text();
			VBox vbox2 = new VBox();
			vbox2.getChildren().addAll(up, currentDate, down);
			vbox2.setSpacing(20);
			HBox hbox44 = new HBox();
			hbox44.getChildren().addAll(l, vbox2);
			hbox44.setSpacing(70);
			Label total = new Label("Total Martyrs in this date is :");
			totalTxt = new Text();
			HBox hbox2 = new HBox();
			hbox2.getChildren().addAll(total, totalTxt);
			hbox2.setSpacing(20);
			Label avg = new Label(" Average martyrs ages in this date is :");
			Text avgTxt = new Text();
			HBox hbox3 = new HBox();
			hbox3.getChildren().addAll(avg, avgTxt);
			hbox3.setSpacing(20);

			Label Dmax = new Label("The district that has the max number of martyrs is :");
			DmaxTxt = new Text();
			HBox hbox4 = new HBox();
			hbox4.getChildren().addAll(Dmax, DmaxTxt);
			hbox4.setSpacing(20);
			Label Lmax = new Label("The location that has the max number of martyrs");
			LmaxTxt = new Text();
			HBox hbox5 = new HBox();
			hbox5.getChildren().addAll(Lmax, LmaxTxt);
			hbox5.setSpacing(20);
			VBox vbox1 = new VBox();
			vbox1.getChildren().addAll(hbox2, hbox3, hbox4, hbox5);
			vbox1.setSpacing(40);
			Button insert = new Button("insert");
			Button update = new Button("update");
			Button delete = new Button("Delete");
			Dinsert = new DatePicker();
			Dinsert.getEditor().setDisable(true);
			DatePicker Dupdate = new DatePicker();
			Dupdate.getEditor().setDisable(true);
			HBox hbox6 = new HBox();
			hbox6.getChildren().addAll(Dinsert, insert, Dupdate, update, delete);
			hbox6.setSpacing(40);
			Button goToMartyr = new Button("Go to martyr Screen");
			VBox vbox3 = new VBox();
			vbox3.getChildren().addAll(hbox1, hbox44, vbox1, hbox6, goToMartyr);
			vbox3.setSpacing(50);
			HBox hbox99 = new HBox();
			data = FXCollections.observableArrayList();
			tv = new ListView<String>(data);

			// fillTV();

			// dateColumn.setCellValueFactory(new PropertyValueFactory<>(date22));
			hbox99.getChildren().addAll(vbox3, tv);
			hbox99.setSpacing(50);
			// dateColumn.setPrefWidth(245);
			BorderPane pane = new BorderPane();
			pane.setCenter(hbox99);
			file.setOnAction(e -> {
				FileChooser fileChooser = new FileChooser();
				f = fileChooser.showOpenDialog(primaryStage);
				try {
					readFile();
					fillTV();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			goToMartyr.setOnAction(e -> {
				martyrScreen martyrScreen = new martyrScreen();
				try {
					martyrScreen.start(new Stage());
					martyrScreen.sethNode(hash.getHashArray(currentIndex));
					martyrScreen.fillTv(hash.getHashArray(currentIndex).getAvlTree().getRoot());
					martyrScreen.getDisCmb().getItems().clear();
					fillDistricts(martyrScreen.getDisCmb());
					martyrScreen.setHeightTxt();
					martyrScreen.setSizeTxt();

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			up.setOnAction(e -> {
				navigateUp();
				try {
					int totalMartyrs = calculateTotalMartyrs(currentDate.getText());
					totalTxt.setText(String.valueOf(totalMartyrs));
				} catch (Exception ex) {
					totalTxt.setText("Error calculating total martyrs: " + ex.getMessage());
				}

				double avgAge = calculateAvgAges(currentDate.getText());
				avgTxt.setText(String.valueOf(avgAge));
				DmaxTxt.setText(hash.getHashArray(currentIndex).getAvlTree().getMaxDistrict());
				LmaxTxt.setText(hash.getHashArray(currentIndex).getAvlTree().getMaxLocation());

				// DmaxTxt.setText(findDistrictWithMaxMartyrs());

			});
			down.setOnAction(e -> {
				navigateDown();
				try {
					int totalMartyrs = calculateTotalMartyrs(currentDate.getText());
					totalTxt.setText(String.valueOf(totalMartyrs));
				} catch (Exception ex) {
					totalTxt.setText("Error calculating total martyrs: " + ex.getMessage());
				}

				double avgAge = calculateAvgAges(currentDate.getText());
				avgTxt.setText(String.valueOf(avgAge));
				DmaxTxt.setText(hash.getHashArray(currentIndex).getAvlTree().getMaxDistrict());
				LmaxTxt.setText(hash.getHashArray(currentIndex).getAvlTree().getMaxLocation());
				// DmaxTxt.setText(findDistrictWithMaxMartyrs());
			});

			insert.setOnAction(e -> {
				LocalDate localDate = Dinsert.getValue();
				Date selectedDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

				SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
				String dateString = dateFormat.format(selectedDate);
				System.out.println("------------------------------------------------------------------");
				if (hash.search(dateString) == -1) {
					hash.insert(dateString, null);

					System.out.println("----------------------------------------------------------------");

					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Insert Successful");
					alert.setHeaderText(null);
					alert.setContentText("Data inserted successfully for date: " + dateString);
					alert.showAndWait();
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("exist");
					alert.setHeaderText(null);
					alert.setContentText("tha date  " + dateString + "  already exist");
					alert.showAndWait();
				}

			});

			search.setOnAction(e -> {

				LocalDate localDate = date.getValue();
				Date selectedDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

				SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
				String dateString = dateFormat.format(selectedDate);

				int index = hash.search(dateString);

				if (index == -1) {
					text.setText("not found");
					totalTxt.setText("no martyrs in this date");
					avgTxt.setText("no martyrs in this date");
					DmaxTxt.setText("no martyrs in this date");
					LmaxTxt.setText("no martyrs in this date");
					currentDate.setText("not found");

				} else {
					text.setText("found");
					currentDate.setText(dateString);
					try {
						int totalMartyrs = calculateTotalMartyrs(currentDate.getText());
						totalTxt.setText(String.valueOf(totalMartyrs));
					} catch (Exception ex) {
						totalTxt.setText("Error calculating total martyrs: " + ex.getMessage());
					}
					double avgMartyrs = calculateAvgAges(currentDate.getText());

					avgTxt.setText(String.valueOf(avgMartyrs));
					DmaxTxt.setText(hash.getHashArray(index).getAvlTree().getMaxDistrict());
					LmaxTxt.setText(hash.getHashArray(index).getAvlTree().getMaxLocation());

				}

			});
			delete.setOnAction(e -> {
				String current = currentDate.getText();
				if (hash.delete(current).equals(current)) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Warning");
					alert.setContentText(
							"Are you sure you want to delete the date  " + currentDate.getText() + "   ??!!");

					// Show confirmation dialog
					alert.showAndWait().ifPresent(response -> {
						if (response == ButtonType.OK) {
							hash.delete(current);
							Alert successAlert = new Alert(AlertType.INFORMATION);
							successAlert.setTitle("Success");
							successAlert.setContentText("The date deleted successfully.");
							successAlert.showAndWait();
						}
					});
				}
			});
			update.setOnAction(e -> {
				// String current = currentDate.getText();
				String current = currentDate.getText();

				LocalDate localDate = Dupdate.getValue();
				Date selectedDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

				SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
				String dateString = dateFormat.format(selectedDate);

				hash.update(current, dateString);

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Success");
				alert.setContentText("The date updated ssuccessfully");
				alert.showAndWait();
				currentDate.setText(dateString);
				try {
					int totalMartyrs = calculateTotalMartyrs(currentDate.getText());
					totalTxt.setText(String.valueOf(totalMartyrs));
				} catch (Exception ex) {
					totalTxt.setText("Error calculating total martyrs: " + ex.getMessage());
				}
				currentIndex = hash.search(dateString);
				double avgAge = calculateAvgAges(currentDate.getText());
				avgTxt.setText(String.valueOf(avgAge));
				DmaxTxt.setText(hash.getHashArray(currentIndex).getAvlTree().getMaxDistrict());
				LmaxTxt.setText(hash.getHashArray(currentIndex).getAvlTree().getMaxLocation());

			});
			Scene scene = new Scene(pane, 1000, 600);

			primaryStage.setScene(scene);
			primaryStage.show();
			// readFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void readFile() throws Exception {
		try (BufferedReader br = new BufferedReader(new FileReader(f))) {
			String[] firstLine = br.readLine().split(",");

			String line;
			while ((line = br.readLine()) != null) {
				String[] tokens = line.split(",");

				// Check if any cell in the row is empty
				boolean isEmptyCell = false;
				for (String token : tokens) {
					if (token.isEmpty()) {
						isEmptyCell = true;
						break;
					}
				}

				// If any cell is empty, skip this row
				if (isEmptyCell) {
					continue;
				}

				String name = tokens[0];
				String dateString = tokens[1]; // Example string representing a date

				SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				Date date = null;
				try {
					date = dateFormat.parse(dateString);
					// System.out.println("Parsed date: " + date);
				} catch (ParseException e) {
					System.out.println("Invalid date format");
				}
				int age = Integer.parseInt(tokens[2]);
				String location = tokens[3];
				String district = tokens[4];
				String gender = tokens[5];
				Martyr martyr = new Martyr(name, age, location, district, gender);
				hash.insert(dateString, martyr);

				if (!tempCmb.getItems().contains(district)) {
					tempCmb.getItems().add(district);
				}
				// dhash.insert(district, martyr);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// System.out.println("dania");
		hash.printHashTable();
	}

	private void fillDistricts(ComboBox<String> cmb) {
		for (int i = 0; i < tempCmb.getItems().size(); i++) {
			cmb.getItems().add(tempCmb.getItems().get(i));
		}
	}

	private void navigateUp() {
		if (currentIndex > 0) {
			HashNode node = hash.getHashArray(--currentIndex);
			while (currentIndex > 0 && node.isEmpty())
				node = hash.getHashArray(--currentIndex);
			// Perform navigation operation based on currentIndex
			// For example, display data at currentIndex
			displayDataAtIndex(currentIndex);
		} else {
			// Handle case where currentIndex is already at the top
		}
	}

	private void navigateDown() {
		if (currentIndex < hash.getSize() - 1) {
			HashNode node = hash.getHashArray(++currentIndex);
			while (currentIndex < hash.getSize() - 1 && node.isEmpty()) {
				node = hash.getHashArray(++currentIndex);
			}
			// Perform navigation operation based on currentIndex
			// For example, display data at currentIndex
			displayDataAtIndex(currentIndex);
		} else {
			// Handle case where currentIndex is already at the bottom
		}
	}

	private void displayDataAtIndex(int index) {
		// Retrieve data at the specified index from the hash table
		HashNode node = hash.getHashArray(index);
//		if (node != null && !node.isEmpty()) {
		currentDate.setText(node.getData());
//		} else {
//			// If no data is found, display a message indicating that no data is available
//			currentDate.setText("No data available for this index");
//		}
	}

	private int calculateTotalMartyrs(String date) {
		// Get the date from currentDate text
		date = currentDate.getText();

		// Find the node in the hash table corresponding to the date
		int index = hash.search(date);
		if (index != -1) {
			HashNode node = hash.getHashArray(index);
			int size = node.getAvlTree().getSize();
			return size;
		} else {
			// If no data is found for the current date, display a message
			totalTxt.setText("No data available for this date");
			return 0; // or any other default value according to your requirement
		}
	}

	private double calculateAvgAges(String date) {
		date = currentDate.getText();
		int index = hash.search(date);
		if (index != -1) {
			HashNode node = hash.getHashArray(index);
			AvlTree avlTree = node.getAvlTree();
			if (avlTree.getSize() == 0) {
				totalTxt.setText("No data available for this date");
				return 0; // or any other default value according to your requirement
			}
			// Traverse the AVL tree to calculate the sum of ages
			int sumAges = traverseTreeAndGetSum(avlTree.getRoot());
			// Calculate the average age
			double avgAge = (double) sumAges / avlTree.getSize();
			return avgAge;
		} else {
			totalTxt.setText("No data available for this date");
			return 0; // or any other default value according to your requirement
		}
	}

	private int traverseTreeAndGetSum(AvlNode node) {
		if (node == null)
			return 0;
		// Recursive traversal to sum up ages
		int leftSum = traverseTreeAndGetSum(node.getLeft());
		int rightSum = traverseTreeAndGetSum(node.getRight());
		// Add current node's age to the sum
		int currentAge = node.getMartyr().getAge();
		return leftSum + rightSum + currentAge;
	}

	public void fillTV() {
		data.clear();
		for (int i = 0; i < hash.getSize(); i++) {
			// hash.getHashArray(i).getData();
			date22 = hash.getHashArray(i).getData();
			if (hash.getHashArray(i).getFlag() == 'F')
				data.add(date22);
		}
	}

//	
//	private String findDistrictWithMaxMartyrs() {
//		// Initialize variables to keep track of the maximum number of martyrs and the
//		// corresponding district
//		int maxMartyrs = 0;
//		String districtWithMaxMartyrs = "";
//
//		// Traverse through all nodes in the hash table
//		for (int i = 0; i < hash.getCapacity(); i++) {
//			HashNode node = hash.getHashArray(i);
//
//			// If the node is not empty
//			if (node != null && !node.isEmpty()) {
//				// Get the AVL tree associated with the node
//				AvlTree avlTree = node.getAvlTree();
//
//				// Get the number of martyrs in the AVL tree
//				int martyrsInTree = avlTree.getSize();
//
//				// If the number of martyrs in the current district is greater than the maximum,
//				// update the maximum
//				if (martyrsInTree > maxMartyrs) {
//					maxMartyrs = martyrsInTree;
//					districtWithMaxMartyrs = node.getAvlTree().getRoot().getMartyr().getDistrict();
//				}
//			}
//		}
//
//		// Return the district with the maximum number of martyrs
//		return districtWithMaxMartyrs;
//	}

	public static void main(String[] args) {
		launch(args);
	}
}
