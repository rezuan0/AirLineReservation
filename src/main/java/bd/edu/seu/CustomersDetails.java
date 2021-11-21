package bd.edu.seu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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

public class CustomersDetails implements Initializable {

    private ObservableList<AirLine> airLines;
    @FXML private TableView<AirLine> AirLineTable;
    @FXML private TableColumn<AirLine, Number> idColumn;
    @FXML private TableColumn<AirLine,String> fnameColumn;
    @FXML private TableColumn<AirLine, String> lnameColumn;
    @FXML private TableColumn<AirLine, String> phoneColumn;
    @FXML private TableColumn<AirLine, String> emailColumn;

    public CustomersDetails(){
        airLines = FXCollections.observableArrayList();
        AirLineTable = new TableView<>();
        idColumn = new TableColumn<>();
        fnameColumn = new TableColumn<>();
        lnameColumn = new TableColumn<>();
        phoneColumn = new TableColumn<>();
        emailColumn = new TableColumn<>();

    }
    @FXML
    private  void homePage5() throws IOException {
        App.setRoot("firstPage");
    }

    @FXML
    private void back() throws IOException {
        App.setRoot("nextAdmin");
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        Connection connection = dataBaseConnection.getConnection();

        try {
            Statement statement = connection.createStatement();

            String query1 = String.format("SELECT C.CUSTID AS CUSTOMER_ID, C.FNAME AS FIRST_NAME," +
                    " C.LNAME AS LAST_NAME, P.PCRTYCODE||'-'||P.PAREACODE||'-'||P.PNUMBER AS PHONE_NO," +
                    " E.EMAIL AS EMAIL FROM CUSTOMER C RIGHT JOIN PHONE P ON C.CUSTID = P.CUSTID RIGHT JOIN" +
                    " EMAIL E ON C.CUSTID = E.CUSTID ORDER BY C.CUSTID");

            ResultSet resultSet = statement.executeQuery(query1);

            while (resultSet.next()){

                int id = Integer.parseInt(resultSet.getString("CUSTOMER_ID"));
                String fName = resultSet.getString("FIRST_NAME");
                String lName = resultSet.getString("LAST_NAME");
                String phone = resultSet.getString("PHONE_NO");
                String email = resultSet.getString("EMAIL");


                AirLine airLine = new AirLine(id,fName,lName,phone,email);

                ArrayList<AirLine> airLineList = new ArrayList<>();
                airLineList.add(airLine);
                airLines.addAll(airLineList);


            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setStyle("-fx-alignment: CENTER-RIGHT;");
        fnameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));



        AirLineTable.setItems(airLines);

    }
}
