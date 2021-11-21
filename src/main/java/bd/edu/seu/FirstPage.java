package bd.edu.seu;

import javafx.fxml.FXML;

import java.io.IOException;

public class FirstPage {

  @FXML
  private void BookingView() throws IOException {
    App.setRoot("seatBooking");
  }
  @FXML
  private void adminLogIn() throws IOException {
    App.setRoot("adminLog");
  }

}
