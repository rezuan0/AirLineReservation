package bd.edu.seu;

import javafx.fxml.FXML;

import java.io.IOException;

public class NextAdmin {

    @FXML
    private void homePage() throws IOException {
        App.setRoot("firstPage");
    }
    @FXML
    private void ShowByCountry() throws IOException {
        App.setRoot("countryDetails");

    }
    @FXML
    private void ShowByCustomer() throws IOException {
        App.setRoot("customersDetails");

    }
    @FXML
    private void ShowByReserved() throws IOException {
        App.setRoot("reservedDetails");

    }
    @FXML
    private void ShowByAllAccounts() throws IOException {
        App.setRoot("AllAccounts");

    }

}
