package bd.edu.seu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static java.lang.Class.forName;

public class NewAccount  {
    @FXML private TextField idField;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField streetField;
    @FXML private TextField cityField;
    @FXML private TextField stateField;
    @FXML private TextField countryField;
    @FXML private TextField postalCodeField;

    @FXML private TextField faxField;
    @FXML private TextField phoneField;
    @FXML private TextField emailAddressField;


    public NewAccount() {
        idField = new TextField();
        firstNameField = new TextField();
        lastNameField = new TextField();
        streetField  = new TextField() ;
        cityField = new TextField();
        stateField = new TextField();
        countryField = new TextField();
        postalCodeField = new TextField();

        faxField = new TextField();
        phoneField = new TextField();
        emailAddressField = new TextField();

    }
    @FXML
    private void seatBook() throws IOException {
        App.setRoot("logIn");
    }
    @FXML
    private void add(){

        int id = Integer.parseInt(idField.getText());
        String fName = firstNameField.getText();
        String lName = lastNameField.getText();
        String street = streetField.getText();
        String city = cityField.getText();
        String state = stateField.getText();
        String country = countryField.getText();
        int postCode = Integer.parseInt(postalCodeField.getText());

        String faxNumber = faxField.getText();
        String phone = phoneField.getText();
        String email = emailAddressField.getText();


        AirLine customer = new AirLine(id,fName,lName,street,city,state,country,postCode);
        AirLine emailT = new AirLine(email,id);
        AirLine phoneT = new AirLine(id,phone);
        //AirLine faxT = new AirLine(faxNumber);


        DataBaseConnection connection = new DataBaseConnection();
        connection.saveToDatabaseForCustomerTable(customer);
        connection.saveToDatabaseForEmailTable(emailT);
        connection.saveToDatabaseForPhoneTable(phoneT);
        //connection.saveToDatabaseForFaxTable(faxNumber,id);

        clear();


    }
    @FXML
    private  void homePage7() throws IOException {
        App.setRoot("firstPage");
    }
    private void clear(){

        Alert alerts = new Alert(Alert.AlertType.INFORMATION);
        alerts.setTitle("Confirmations...");
        alerts.setHeaderText(null);
        alerts.setContentText("Account Created... ");
        alerts.showAndWait();

        idField.clear();
        firstNameField.clear();
        lastNameField.clear();
        streetField.clear();
        cityField.clear();
        stateField.clear();
        countryField.clear();
        postalCodeField.clear();

        faxField.clear();
        phoneField.clear();
        emailAddressField.clear();
    }

}
