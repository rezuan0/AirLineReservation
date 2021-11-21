package bd.edu.seu;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.lang.invoke.LambdaMetafactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ticket {

    @FXML private TextField idField;
    @FXML private TextArea areaField;

    public Ticket(){
        idField = new TextField();
        areaField = new TextArea();
    }
    String fastName;
    String lastName;
    String source;
    String destination;
    String fname;
    String fcode;
    String date;
    String time;
    String cls;
    String seat;
    String am;
    double amount;

    DataBaseConnection dataBaseConnection = new DataBaseConnection();
    Connection connection = dataBaseConnection.getConnection();
    @FXML
    private void print() {
        int id = Integer.parseInt(idField.getText());

        try {
            Statement statement = connection.createStatement();

            String query = String.format("select fname,lname from customer where custid = %d", id);

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                fastName = resultSet.getString("fname");
                lastName = resultSet.getString("lname");

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            Statement statement = connection.createStatement();

            String query1 = String.format("select * from reserved where id = %d", id);
            ResultSet resultSet1 = statement.executeQuery(query1);

            while (resultSet1.next()) {

                source = resultSet1.getString("source");
                destination = resultSet1.getString("destination");
                fname = resultSet1.getString("Fname");
                fcode = resultSet1.getString("Fcode");
                date = resultSet1.getString("Date");
                time = resultSet1.getString("Time");
                cls = resultSet1.getString("Class");
                seat = resultSet1.getString("seats");
                am = resultSet1.getString("Payed");


            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (am == null ) {
            Alert alerts = new Alert(Alert.AlertType.WARNING);
            alerts.setTitle("Payment...");
            alerts.setHeaderText(null);
            alerts.setContentText("Not payed yet... ");
            alerts.showAndWait();
        }
        else{
            amount = Double.parseDouble(am);
            areaField.setText("                                                               " +
                    "AirLine Reservation System\n" +
                    "                                                               " +
                    "                Ticket\n" + "\n" +
                    "             First Name:        " + fastName + " " + "                              Last Name:         " + lastName + "\n" +
                    "\n" +
                    "             Date:                 " + date + " " + "                   Time:               " + time + "\n" + "\n" +

                    "             Class:                " + cls + " " + "                                 Flight Name:   " + fname + "\n" + "\n" +
                    "             Flight Code:       " + fcode + " " + "                    Seats:               " + seat + "\n" + "\n" +
                    "             Source:               " + source + "                        Destination:      " + destination + "\n" + "\n" +
                    "             Total Cost:        " + amount +"  $"+ "\n" + "\n");


        }
    }
    @FXML
    private void back() throws IOException {
        App.setRoot("payment");
    }

    @FXML
    private  void homePage() throws IOException {
        App.setRoot("firstPage");
    }

}
