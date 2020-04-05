package sample;

import com.sun.prism.impl.Disposer.Record;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Controller {

/*  @FXML
  private ComboBox<String> location;*/

  int hours;
  int ageRangeA;
  int ageRangeB;
  double durationA;
  double durationB;


  @FXML
  private Button search;
  @FXML
  private Button export;

  @FXML
  private ComboBox<String> gender;

  @FXML
  private ComboBox<String> time;

  @FXML
  private ComboBox<String> day;

  @FXML
  private ComboBox<String> age;

  @FXML
  private ComboBox<String> duration;
  @FXML
  private ComboBox<String> place;
  @FXML
  private Label result;
  @FXML
  private Tab tab2;
  @FXML
  private TableView<Record> outputTable;

  @FXML
  private TableColumn<Record, String> tableEmail;

  @FXML
  private TableColumn<Record, String> tableAge;

  @FXML
  private TableColumn<Record, String> tableLocation;

  @FXML
  private TableColumn<Record, String> tableGender;

  @FXML
  private TableColumn<Record, String> tableHours;

  @FXML
  private TableColumn<Record, String> tableTime;

  String periodOfDayFinal;
  String genderFinal;
  String locationFinal;
  String location;
  String genderResult;
  String ageRange;
  String periodOfDay;
  String durationOfStay;
  private ObservableList<Record> logList;

  @FXML
  private void initialize(){
    this.setComboBoxText();

  }

  private void setComboBoxText() {
    this.gender.setPromptText("Select a type of gender.");
    this.gender.getItems().addAll(new String[]{"All", "Male", "Female"});
    this.time.setPromptText("Select a type of time period.");
    this.time.getItems().addAll(new String[]{"All", "Morning", "Afternoon", "Evening"});
    this.age.setPromptText("Select a type of age.");
    this.age.getItems().addAll(new String[]{"All","18-27","28-39","40-55","55+"});
    this.place.setPromptText("Select a type of Place.");
    this.place.getItems().addAll(new String[]{"All" ,"A", "B"});
    this.duration.getItems().addAll(new String[]{"All" ,"0.5-4 hours","5-8 hours","9-12 hours","13-16 hours","17-20 hours","21-24 hours"});
   /* int i;
    for(i = 0; i < 15; ++i) {
      this.duration.getItems().add(String.valueOf(i + 1));
    }*/

  }


  public void getData(ActionEvent actionEvent) throws Exception {
    result.setText(" ");
    durationOfStay = duration.getValue();
    System.out.println(durationOfStay);
    location = place.getValue();
    genderResult = gender.getValue();
    ageRange = age.getValue();
    periodOfDay = time.getValue();
    queryData();
    DatabaseManager db = new DatabaseManager();
    System.out.println(locationFinal+genderFinal);
    TableView<Record> tableView = new TableView<>();

    boolean passed = db.QueryMethod(locationFinal,genderFinal,durationA,durationB,periodOfDayFinal,ageRangeA,ageRangeB);
    if(passed){

      result.setText("Based on the query there are " + db.numRows + " result");
      export.setVisible(true);





    }
    else{
      Main.errorMessage("The data is not available to export" );    }
  }

  @FXML
  void transfer(ActionEvent event) throws Exception {
    DatabaseManager db = new DatabaseManager();
    Main.infoMessage("This step will take it to the payment screen and next it will allow the csv file created to be emailed. There will be also custome table view displayed for that");


  }
  public List<?> queryData(){




    {
      if(periodOfDay.equals("All")){
        periodOfDayFinal = "Morning' OR timeoftheday = 'Afternoon' OR timeoftheday= 'Evening";
      }
      else {
        periodOfDayFinal = periodOfDay;
      }
    }
    {
      if(location.equals("All")){
        locationFinal = "A' OR LOCATION = 'B";
      }
      else {
        locationFinal = location;
      }
    }
    {
      if(genderResult.equals("All")){
        genderFinal = "Male' OR GENDER = 'Female";

      }
      else {
        genderFinal = genderResult;

      }
    }
    {
    if(ageRange.equals("18-27")){
      ageRangeA = 18;
      ageRangeB = 27;
    }
    else if(ageRange.equals("28-39")){
      ageRangeA = 28;
      ageRangeB = 39;
    }
    else if(ageRange.equals("40-55")){
      ageRangeA = 40;
      ageRangeB = 55;
    }
    else if(ageRange.equals("55+")){
      ageRangeA = 56;
      ageRangeB = 199;
    }
    else {
      ageRangeA = 1;
      ageRangeB = 199;
    }}
    {
      if (durationOfStay.equals("0.5-4 hours")){
        durationA = 0.5;
        durationB =4.99;
    }
      else if(durationOfStay.equals("5-8 hours")){
        durationA = 5.00;
        durationB =8.99;
      }
      else if(durationOfStay.equals("9-12 hours")){
        durationA = 9.00;
        durationB =12.99;
      }
      else if(durationOfStay.equals("13-16 hours")){
        durationA = 13.00;
        durationB = 16.99;
      }
      else if(durationOfStay.equals("17-20 hours")){
        durationA = 17.00;
        durationB = 20.99;
      }
      else if(durationOfStay.equals("21-24 hours")){
        durationA = 21.00;
        durationB = 24.00;
      }
      else if(durationOfStay.equals("All")){
        durationA = 00.01;
        durationB = 24.00;
      }
    }
return (List<?>) Arrays.asList(locationFinal,genderFinal, durationA,durationB, periodOfDayFinal, ageRangeA,ageRangeB);
  }










}
