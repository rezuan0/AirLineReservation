package bd.edu.seu;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public class Payment {

    @FXML
    private TextField idField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField sourceField;
    @FXML
    private TextField destinationField;
    @FXML
    private TextField FLnameField;
    @FXML
    private TextField NumberOfSeatsField;
    @FXML
    private TextField classField;
    @FXML
    private TextField dateField;
    @FXML
    private TextField timeField;
    @FXML
    private TextField totalCostField;
    @FXML
    private TextField payField;

    double cost;
    String fName;
    String lName;


    public Payment() {
        idField = new TextField();
        firstNameField = new TextField();
        lastNameField = new TextField();
        sourceField = new TextField();
        destinationField = new TextField();
        FLnameField = new TextField();
        NumberOfSeatsField = new TextField();
        classField = new TextField();

        dateField = new TextField();
        timeField = new TextField();
        totalCostField = new TextField();
        payField = new TextField();

    }
    @FXML
    private  void homePage8() throws IOException {
        App.setRoot("firstPage");
    }

    DataBaseConnection dataBaseConnection = new DataBaseConnection();
    Connection connection = dataBaseConnection.getConnection();

    @FXML
    private void search() {
        int i = 0;
        int id = Integer.parseInt(idField.getText());


        try {
            Statement statement = connection.createStatement();

            String query = String.format("select fname,lname from customer where custid = %d", id);

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                fName = resultSet.getString("fname");
                lName = resultSet.getString("lname");


            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            Statement statement = connection.createStatement();

            String query1 = String.format("select * from reserved where id = %d", id);
            ResultSet resultSet1 = statement.executeQuery(query1);

            while (resultSet1.next()) {

                String cls = resultSet1.getString("Class");
                int seat = Integer.parseInt(resultSet1.getString("seats"));

                firstNameField.setText(fName);
                lastNameField.setText(lName);
                sourceField.setText(resultSet1.getString("source"));
                destinationField.setText(resultSet1.getString("destination"));
                FLnameField.setText(resultSet1.getString("fname"));
                NumberOfSeatsField.setText(resultSet1.getString("seats"));
                dateField.setText(resultSet1.getString("Date"));
                timeField.setText(resultSet1.getString("Time"));
                classField.setText(resultSet1.getString("Class"));

                if (cls.equals("1st")) {
                    cost = seat * 350;
                    totalCostField.setText(String.format("%.2f", cost));
                } else if (cls.equals("BUS")) {
                    cost = seat * 305 * 1.5;
                    totalCostField.setText(String.format("%.2f", cost));
                } else {
                    cost = seat * 300;
                    totalCostField.setText(String.format("%.2f", cost));
                }
                i++;

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (i == 0) {

            Alert alerts = new Alert(Alert.AlertType.WARNING);
            alerts.setTitle("Customer ID...");
            alerts.setHeaderText("Customer ID not Valid !!!");
            alerts.setContentText("Enter Valid ID... ");
            alerts.showAndWait();
        }


    }

    @FXML
    private void pay() throws SQLException, IOException {
        double amount = Double.parseDouble(payField.getText());
        int id = Integer.parseInt(idField.getText());
        if (amount == cost) {
            Statement statement = connection.createStatement();

            String query = String.format("update reserved set payed = %.2f where id = %d", amount, id);

            statement.executeUpdate(query);

            Alert alerts = new Alert(Alert.AlertType.CONFIRMATION);
            alerts.setTitle("Payment...");
            alerts.setHeaderText(null);
            alerts.setContentText("Payment received Successfully... ");
            alerts.showAndWait();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Ticket Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Do you want to print ticket ?");
            Optional<ButtonType> yes = alert.showAndWait();
            if (yes.get() == ButtonType.OK) {
                App.setRoot("ticket");
            } else {
                App.setRoot("firstPage");
            }

        } else {
            Alert alerts = new Alert(Alert.AlertType.ERROR);
            alerts.setTitle("Payment...");
            alerts.setHeaderText(null);
            alerts.setContentText("Amount don't mach.. \n Enter exact amount... ");
            alerts.showAndWait();
        }
    }
}
