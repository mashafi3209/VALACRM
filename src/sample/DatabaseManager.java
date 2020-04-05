package sample;

import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DatabaseManager  extends Main {
  private Connection con = null;
  int numRows = 0;
  public DatabaseManager() throws SQLException {
    this.con = DriverManager.getConnection("jdbc:h2:C:\\Users\\shafi\\IdeaProjects\\VALACRM\\res\\dataTable");
  }



  public List<String> resultSetArray=new ArrayList<>();
  public boolean QueryMethod(String place, String gender, Double hoursA, Double hoursB, String period, int ageA, int ageB)
      throws Exception {
    Statement stmt = this.con.createStatement();

/*
    String query = String.format("Select * FROM CUSTOMERDATA WHERE (LOCATION = %s )AND (GENDER = %s ) AND (HOURS between =%d and =%d) AND (TIMEOFTHEDAY =%s )AND (AGE between =%d and =%d)",place,gender,hoursA,hoursB,period,ageA,ageB);
*/

 /*   stmt.setString(1,place);
    stmt.setDouble(3,hoursA);
    stmt.setDouble(4,hoursB);

    stmt.setString(2,gender);

    stmt.setInt(6,ageA);
    stmt.setInt(7,ageB);

    stmt.setString(5,period);*/

    System.out.println("1 " +place);
    System.out.println("2 "+ gender);

    System.out.println("4 "+ period);
    System.out.println("5 "+ ageB);
    System.out.println("6 "+ ageA);

    System.out.println("10 "+ hoursA);
    System.out.println("11 "+ hoursB);
/*
    System.out.println(hoursA);
    System.out.println(hoursB);
    System.out.println(period);
    System.out.println(ageA);

*/

/*
    System.out.printf("Select * FROM CUSTOMERDATA WHERE (LOCATION = %s " + ")AND (GENDER = %s "+ ") AND (HOURS between %f " + " and %f" + ")"
        + " AND (TIMEOFTHEDAY = %s"+ " )AND (AGE between %d"+" and %d" +")", place, gender, hoursA, hoursB, period, ageA, ageB);*/
    ResultSet rs = stmt.executeQuery(String.format("Select * FROM CUSTOMERDATA WHERE (LOCATION = '%s' )AND (GENDER = '%s' ) AND (HOURS between %f and %f) AND (TIMEOFTHEDAY = '%s' )AND (AGE between %d and %d)",place,gender,hoursA,hoursB,period,ageA,ageB));
    int numCols = rs.getMetaData().getColumnCount();

    while(rs.next()) {
      StringBuilder sb = new StringBuilder();

      for (int i = 1; i <= numCols; i++) {
        StringBuilder temp = sb.append(String.format(String.valueOf(rs.getString(i))) + ", ");
        System.out.println(temp);


      }
      resultSetArray.add(sb.toString());


    }
    printToCsv();
    return true;
  }




  public void printToCsv() throws Exception{
    System.out.println("check " +resultSetArray);
    String file_name =  "./Data.csv";
    File csvOutputFile = new File(file_name);
    FileWriter fileWriter = new FileWriter(csvOutputFile, false);


    for(String mapping :resultSetArray) {
      fileWriter.write(mapping + "\n");
      numRows += 1;
    }

    fileWriter.close();

  }
}
