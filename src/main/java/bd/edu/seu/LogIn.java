package bd.edu.seu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class LogIn implements Initializable {

    ObservableList<String> sourceList = FXCollections.observableArrayList("Toronto",
            "New York","London","Paris","Bonn","Rome");

    ObservableList<String> destinationList = FXCollections.observableArrayList("Montreal",
            "Chicago","Edinburgh","Nice","Berlin","Naples");

    private ObservableList<Booking> bookings;

    @FXML private TextField idField;
    @FXML private  TextField seatField;
    @FXML private ComboBox<String> comboBoxS;
    @FXML private ComboBox<String> comboBoxD;

    @FXML private TableView<Booking> bookingTable;
    @FXML private TableColumn<Booking, String> sourceColumn;
    @FXML private TableColumn<Booking, String> destinationColumn;
    @FXML private TableColumn<Booking,String> fameColumn;
    @FXML private TableColumn<Booking, String> fcodeColumn;
    @FXML private TableColumn<Booking, String> dateColumn;
    @FXML private TableColumn<Booking, String> timeColumn;
    @FXML private TableColumn<Booking,String> classColumn;
    @FXML private TableColumn<Booking, String> sm_IColumn;

    public LogIn() {
        idField = new TextField();
        seatField = new TextField();

        bookings = FXCollections.observableArrayList();
          bookingTable = new TableView<>();
          sourceColumn = new TableColumn<>();
          destinationColumn = new TableColumn<>();
         fameColumn = new TableColumn<>();
         fcodeColumn = new TableColumn<>();
         dateColumn = new TableColumn<>();
         timeColumn = new TableColumn<>();
         classColumn = new TableColumn<>();
         sm_IColumn = new TableColumn<>();

    }

    @FXML
    public void find(){
        //int id = Integer.parseInt(idField.getText());
        String source = comboBoxS.getSelectionModel().getSelectedItem();
        String destination = comboBoxD.getSelectionModel().getSelectedItem();


        initial(source,destination);

    }


    public ArrayList<Booking> bookingList(String source, String dest){

        ArrayList<Booking> bookingList = new ArrayList<>();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        Connection connection = dataBaseConnection.getConnection();


        try {
            Statement statement = connection.createStatement();

            String query1 = String.format("select * from booking where source = \"%s\" and destination = \"%s\"",
                    source,dest);

            ResultSet resultSet = statement.executeQuery(query1);

                int i=0;
                while (resultSet.next()){

                    String sourc = resultSet.getString("source");
                    String destination = resultSet.getString("destination");
                    String fname = resultSet.getString("Fname");
                    String fcode = resultSet.getString("Fcode");
                    String date = resultSet.getString("Date");
                    String time = resultSet.getString("Time");
                    String cls = resultSet.getString("Class");
                    String SM_I = resultSet.getString("SM_I");

                    Booking booking = new Booking(sourc,destination,fname,fcode,
                            LocalDate.parse(date), LocalTime.parse(time),
                            cls,SM_I);

                    bookingList.add(booking);
                    i++;

                }
            if(i==0){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error...");
                alert.setHeaderText(null);
                alert.setContentText("No Flight Available ... ");
                alert.showAndWait();
            }



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return bookingList;
    }
    @FXML
    private  void homePage6() throws IOException {
        App.setRoot("firstPage");
    }

    @FXML
    private void selectAction() throws IOException {
        int id = Integer.parseInt(idField.getText());

        Booking selectItem = bookingTable.getSelectionModel().getSelectedItem();

        TextInputDialog alert = new TextInputDialog();
        alert.setTitle("Seats...");
        alert.setHeaderText(null);
        alert.setContentText("Insert The Number of Seats ");

        Optional<String> result = alert.showAndWait();
        if(result.isPresent()){
            int seat = Integer.parseInt(result.get());

            DataBaseConnection dataBaseConnection = new DataBaseConnection();
            int i = dataBaseConnection.saveToDatabaseForReservationTable(selectItem,id,seat);
            if(i == 1){
                Alert alerts = new Alert(Alert.AlertType.INFORMATION);
                alerts.setTitle("Confirmations...");
                alerts.setHeaderText(null);
                alerts.setContentText("Reservation Confirm... ");
                alerts.showAndWait();
            }
        }
        Alert aler = new Alert(Alert.AlertType.CONFIRMATION);
        aler.setTitle("Confirmation Dialog");
        aler.setHeaderText(null);
        aler.setContentText("Are you want to pay now ?");
        Optional<ButtonType> yes = aler.showAndWait();

        if(yes.get()==ButtonType.OK){
            App.setRoot("payment");
        }
        else {
            App.setRoot("firstPage");

        }


    }

    public void initial(String source,String destination){

        sourceColumn.setCellValueFactory(new PropertyValueFactory<>("source"));
        destinationColumn.setCellValueFactory(new PropertyValueFactory<>("destination"));
        fameColumn.setCellValueFactory(new PropertyValueFactory<>("Fname"));
        fcodeColumn.setCellValueFactory(new PropertyValueFactory<>("Fcode"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        classColumn.setCellValueFactory(new PropertyValueFactory<>("Cls"));
        sm_IColumn.setCellValueFactory(new PropertyValueFactory<>("SM_I"));

        List<Booking> bookings1 = bookingList(source,destination);
        bookings.addAll(bookings1);

        bookingTable.setItems(bookings);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
        comboBoxS.setItems(sourceList);
        comboBoxD.setItems(destinationList);

    }
}
