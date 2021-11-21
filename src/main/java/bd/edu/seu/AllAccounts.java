package bd.edu.seu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
import java.util.ResourceBundle;

public class AllAccounts implements Initializable {

    private ObservableList<AirLine> AirLines;
    @FXML private TableView<AirLine> AirLineTable;

    @FXML private TableColumn<AirLine, Number> idColumn;
    @FXML private TableColumn<AirLine,String> FnameColumn;
    @FXML private TableColumn<AirLine, String> LnameColumn;
    @FXML private TableColumn<AirLine, String> StreetColumn;
    @FXML private TableColumn<AirLine, String> cityColumn;
    @FXML private TableColumn<AirLine,String> provinceColumn;
    @FXML private TableColumn<AirLine, String> countryColumn;
    @FXML private TableColumn<AirLine, String> postCodeColumn;


    public AllAccounts(){
        AirLines = FXCollections.observableArrayList();
        AirLineTable = new TableView<>();

        idColumn = new TableColumn<>();
        FnameColumn = new TableColumn<>();
        LnameColumn = new TableColumn<>();
        StreetColumn = new TableColumn<>();
        cityColumn = new TableColumn<>();
        provinceColumn = new TableColumn<>();
        countryColumn = new TableColumn<>();
        postCodeColumn = new TableColumn<>();

    }

    @FXML
    private void back() throws IOException {
        App.setRoot("nextAdmin");
    }

    @FXML
    private  void homePage2() throws IOException {
        App.setRoot("firstPage");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        Connection connection = dataBaseConnection.getConnection();

        try {
            Statement statement = connection.createStatement();

            String query1 = String.format("select * from customer");

            ResultSet resultSet = statement.executeQuery(query1);

            while (resultSet.next()){

                int id = Integer.parseInt(resultSet.getString("CUSTID"));
                String fName = resultSet.getString("FNAME");
                String LName = resultSet.getString("LNAME");
                String street = resultSet.getString("STREET");
                String city = resultSet.getString("CITY");
                String province = resultSet.getString("PROVINCE");
                String country = resultSet.getString("COUNTRY");
                int postCode = Integer.parseInt(resultSet.getString("POSTCODE"));


                AirLine AirLine = new AirLine(id,fName,LName,street,city,province,country,postCode);

                ArrayList<AirLine> AirLineList = new ArrayList<>();
                AirLineList.add(AirLine);
                AirLines.addAll(AirLineList);


            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setStyle("-fx-alignment: CENTER-RIGHT;");
        FnameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        LnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        StreetColumn.setCellValueFactory(new PropertyValueFactory<>("street"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        provinceColumn.setCellValueFactory(new PropertyValueFactory<>("state"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        postCodeColumn.setCellValueFactory(new PropertyValueFactory<>("postCode"));


        AirLineTable.setItems(AirLines);

    }
}
