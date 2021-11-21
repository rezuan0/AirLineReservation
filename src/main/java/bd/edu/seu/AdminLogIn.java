package bd.edu.seu;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AdminLogIn {

   @FXML private TextField usernameField;
   @FXML private PasswordField passwordField;

    public AdminLogIn() {
        usernameField = new TextField();
        passwordField = new PasswordField();
    }
    @FXML
    private void logIn() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String user = "admin";
        String pass = "admin";
        if(username.equalsIgnoreCase(user) && password.equalsIgnoreCase(pass)){
            App.setRoot("nextAdmin");
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error...");
            alert.setHeaderText(null);
            alert.setContentText("Wrong Username & Password ... ");
            alert.showAndWait();

            usernameField.clear();
            passwordField.clear();
        }
    }
    @FXML
    private  void home1() throws IOException {
        App.setRoot("firstPage");
    }
}
