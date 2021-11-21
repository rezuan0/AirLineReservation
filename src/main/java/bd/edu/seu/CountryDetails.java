package bd.edu.seu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CountryDetails {

    private ObservableList<AirLine> countryT;
    @FXML
    private TextField countryField;

    @FXML
    private TableView<AirLine> tableView;
    @FXML
    private TableColumn<AirLine, Number> idColumn;
    @FXML
    private TableColumn<AirLine, String> countryColumn;

    public CountryDetails() {

        countryField = new TextField();
        tableView = new TableView<>();
        idColumn = new TableColumn<>();
        countryColumn = new TableColumn<>();

        countryT = FXCollections.observableArrayList();

    }

    @FXML
    private void search() {
        String country = countryField.getText();

        initial(country);
    }

    @FXML
    private void back() throws IOException {
        App.setRoot("nextAdmin");
    }

    public void initial(String country) {

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setStyle("-fx-alignment: CENTER-RIGHT;");


        List<AirLine> countryList = countryList(country);
        countryT.addAll(countryList);

        tableView.setItems(countryT);

    }

    @FXML
    private void homePage4() throws IOException {
        App.setRoot("firstPage");
    }

    public ArrayList<AirLine> countryList(String country) {

        int i = 0;
        ArrayList<AirLine> countryList = new ArrayList<>();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        Connection connection = dataBaseConnection.getConnection();

        try {
            Statement statement = connection.createStatement();

            String query1 = String.format("SELECT CUSTID AS CUSTOMER_ID FROM CUSTOMER WHERE COUNTRY " +
                    "= '%s' ORDER BY CUSTID", country);

            ResultSet resultSet = statement.executeQuery(query1);


            while (resultSet.next()) {

                int id = Integer.parseInt(resultSet.getString("CUSTOMER_ID"));

                AirLine airLine = new AirLine(id);
                countryList.add(airLine);

                i++;

            }
            if(i==0){
                Alert alerts = new Alert(Alert.AlertType.WARNING);
                alerts.setTitle("Country Dialogue...");
                alerts.setHeaderText(null);
                alerts.setContentText("Not Found... ");
                alerts.showAndWait();

                countryField.clear();
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return countryList;
    }
}
