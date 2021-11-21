package bd.edu.seu;

import javafx.fxml.FXML;

import java.io.IOException;

public class SeatBooking {

    @FXML
    private void newAccount() throws IOException {
        App.setRoot("newAccount");
    }

    @FXML
    private void seatBook() throws IOException {
        App.setRoot("logIn");
    }
    @FXML
    private void Ticket() throws IOException {
        App.setRoot("ticket");
    }
    @FXML
    private void payment() throws IOException {
        App.setRoot("payment");
    }
    @FXML
    private void cancelBooking() throws IOException {
        App.setRoot("CancelBooking");
    }
    @FXML
    private  void homePage() throws IOException {
        App.setRoot("firstPage");
    }
}
