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

public class ReservedDetails implements Initializable {

    private ObservableList<Reserved> Reserveds;
    @FXML private TableView<Reserved> ReservedTable;
    @FXML private TableColumn<Reserved, Number> idColumn;
    @FXML private TableColumn<Reserved,String> sourceColumn;
    @FXML private TableColumn<Reserved, String> destinationColumn;
    @FXML private TableColumn<Reserved, String> FLnameColumn;
    @FXML private TableColumn<Reserved, String> FLcodeColumn;
    @FXML private TableColumn<Reserved,String> dateColumn;
    @FXML private TableColumn<Reserved, String> timeColumn;
    @FXML private TableColumn<Reserved, String> classColumn;
    @FXML private TableColumn<Reserved, String> SM_IColumn;
    @FXML private TableColumn<Reserved, String> seatColumn;
    @FXML private TableColumn<Reserved, String> payedColumn;

    public ReservedDetails(){
        Reserveds = FXCollections.observableArrayList();
        ReservedTable = new TableView<>();

        idColumn = new TableColumn<>();
        sourceColumn = new TableColumn<>();
        destinationColumn = new TableColumn<>();
        FLnameColumn = new TableColumn<>();
        FLcodeColumn = new TableColumn<>();
        dateColumn = new TableColumn<>();
        timeColumn = new TableColumn<>();
        classColumn = new TableColumn<>();
        SM_IColumn = new TableColumn<>();
        seatColumn = new TableColumn<>();
        payedColumn = new TableColumn<>();


    }

    @FXML
    private void back() throws IOException {
        App.setRoot("nextAdmin");
    }
    @FXML
    private  void homePage() throws IOException {
        App.setRoot("firstPage");
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        Connection connection = dataBaseConnection.getConnection();

        try {
            Statement statement = connection.createStatement();

            String query1 = String.format("select * from reserved");

            ResultSet resultSet = statement.executeQuery(query1);

            while (resultSet.next()){

                int id = Integer.parseInt(resultSet.getString("id"));
                String source = resultSet.getString("source");
                String destination = resultSet.getString("destination");
                String flName = resultSet.getString("fname");
                String flCode = resultSet.getString("fcode");
                LocalDate date = LocalDate.parse(resultSet.getString("date"));
                LocalTime time = LocalTime.parse(resultSet.getString("time"));
                String cls = resultSet.getString("class");
                String sm_I = resultSet.getString("SM_I");
                int seat = Integer.parseInt(resultSet.getString("seats"));
                double payed = Double.parseDouble(resultSet.getString("Payed"));


                Reserved Reserved = new Reserved(id,source,destination,flName,flCode,date,time,cls,sm_I,seat,payed);

                ArrayList<Reserved> ReservedList = new ArrayList<>();
                ReservedList.add(Reserved);
                Reserveds.addAll(ReservedList);


            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setStyle("-fx-alignment: CENTER-RIGHT;");
        sourceColumn.setCellValueFactory(new PropertyValueFactory<>("source"));
        destinationColumn.setCellValueFactory(new PropertyValueFactory<>("destination"));
        FLnameColumn.setCellValueFactory(new PropertyValueFactory<>("flName"));
        FLcodeColumn.setCellValueFactory(new PropertyValueFactory<>("flCode"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        classColumn.setCellValueFactory(new PropertyValueFactory<>("cls"));
        SM_IColumn.setCellValueFactory(new PropertyValueFactory<>("SM_I"));
        seatColumn.setCellValueFactory(new PropertyValueFactory<>("seat"));
        payedColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));



        ReservedTable.setItems(Reserveds);

    }
}
