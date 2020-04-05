package sample;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TableViewClass {
  public class Record {


    private SimpleStringProperty tEmail, tAge, tLocation, tGender, tDuration, tTime;

    public String gettEmail() {
      return tEmail.get();
    }

    public String gettAge() {
      return tAge.get();
    }

    public String gettLocation() {
      return tLocation.get();
    }

    public String gettGender() {
      return tGender.get();
    }

    public String gettDuration() {
      return tDuration.get();
    }

    public String gettTime() {
      return tTime.get();
    }

    Record(String tEmail, String tAge, String tLocation, String tGender,
        String tDuration, String tTime) {
      this.tEmail = new SimpleStringProperty(tEmail);
      this.tAge = new SimpleStringProperty(tAge);
      this.tLocation = new SimpleStringProperty(tLocation);
      this.tGender = new SimpleStringProperty(tGender);
      this.tDuration = new SimpleStringProperty(tDuration);
      this.tTime = new SimpleStringProperty(tTime);
    }


  }

  private final TableView<Record> tableView = new TableView<>();

  private final ObservableList<Record> dataList
      = FXCollections.observableArrayList();


  public void TableView(Stage primaryStage) {
    primaryStage.setTitle("java-buddy.blogspot.com");

    Group root = new Group();

    TableColumn columnF1 = new TableColumn("f1");
    columnF1.setCellValueFactory(
        new PropertyValueFactory<>("f1"));

    TableColumn columnF2 = new TableColumn("f2");
    columnF2.setCellValueFactory(
        new PropertyValueFactory<>("f2"));

    TableColumn columnF3 = new TableColumn("f3");
    columnF3.setCellValueFactory(
        new PropertyValueFactory<>("f3"));

    TableColumn columnF4 = new TableColumn("f4");
    columnF4.setCellValueFactory(
        new PropertyValueFactory<>("f4"));

    TableColumn columnF5 = new TableColumn("f5");
    columnF5.setCellValueFactory(
        new PropertyValueFactory<>("f5"));

    TableColumn columnF6 = new TableColumn("f6");
    columnF6.setCellValueFactory(
        new PropertyValueFactory<>("f6"));

    tableView.setItems(dataList);
    tableView.getColumns().addAll(
        columnF1, columnF2, columnF3, columnF4, columnF5, columnF6);

    VBox vBox = new VBox();
    vBox.setSpacing(10);
    vBox.getChildren().add(tableView);

    root.getChildren().add(vBox);

    primaryStage.setScene(new Scene(root, 700, 250));
    primaryStage.show();

    readCSV();
  }

  private void readCSV() {

    String CsvFile = "./Data.csv";
    String FieldDelimiter = ",";

    BufferedReader br;

    try {
      br = new BufferedReader(new FileReader(CsvFile));

      String line;
      while ((line = br.readLine()) != null) {
        String[] fields = line.split(FieldDelimiter, -1);

        Record record = new Record(fields[0], fields[1], fields[2],
            fields[3], fields[4], fields[5]);
        System.out.println(record);
        dataList.add(record);


      }

    } catch (FileNotFoundException ex) {
      Main.errorMessage("R");
    } catch (IOException ex) {
      Main.errorMessage("T");
    }

  }

}
