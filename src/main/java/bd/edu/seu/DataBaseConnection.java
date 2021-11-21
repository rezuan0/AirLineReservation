package bd.edu.seu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import  java.lang.String;
import java.util.Queue;

public class DataBaseConnection {

    private String DB_URL = "jdbc:mysql://localhost/project";
    private String DB_User = "javap";
    private String DB_Pass = "java";

    public Connection getConnection(){
        try {
            Connection connection = DriverManager.getConnection(DB_URL,DB_User,DB_Pass);
            System.out.printf("Connection Okay !!!\n");
            return connection;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void saveToDatabaseForCustomerTable(AirLine airLine){
        Connection connection = getConnection();

        try {
            Statement statement = connection.createStatement();

            String query = String.format(" insert into customer values(%d, \"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\", %d)",
                    airLine.getId(),airLine.getFirstName(),
                    airLine.getLastName(),airLine.getStreet(),airLine.getCity(),
                    airLine.getState(),airLine.getCountry(),airLine.getPostCode());
            String country = airLine.getCountry();
            String f2 = country.substring(0,2);
            String query1 = String.format("insert into country values(\"%s\", \"%s\")",f2,country);

            statement.executeUpdate(query);
            statement.executeUpdate(query1);


            //System.out.printf("Inserted into databases \n");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveToDatabaseForEmailTable(AirLine emailTable){
        Connection connection = getConnection();

        try {
            Statement statement = connection.createStatement();

            String query = String.format("insert into email values(\"%s\", %d)",
                    emailTable.getEmail(),emailTable.getId());

            statement.executeUpdate(query);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void saveToDatabaseForPhoneTable(AirLine phoneT){
        Connection connection = getConnection();

        try {
            String phone = phoneT.getPhone();
            Statement statement = connection.createStatement();
            String fp = "880";
            String s = phone.substring(1,2);
            String f = phone.substring(3,10);

            String query = String.format("insert into phone values(%s, %s, %s, %d)",
                    fp,s,f,phoneT.getId());

            statement.executeUpdate(query);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public int saveToDatabaseForReservationTable(Booking countryT,int id, int seat){
        Connection connection = getConnection();

        try {
            Statement statement = connection.createStatement();
            String query = String.format("insert into reserved values(%d, \"%s\", \"%s\", \"%s\", \"%s\"," +
                            " \"%s\", \"%s\", \"%s\", \"%s\", %d,null)",

                    id,countryT.getSource(),countryT.getDestination(),
                    countryT.getFname(),countryT.getFcode(),countryT.getDate().toString(),
                    countryT.getTime().toString(),countryT.getCls(),countryT.getSM_I(),seat);

                statement.executeUpdate(query);
            return 1;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return 0;
    }

    public void saveToDatabaseForFaxTable(String faxT, int id){
        Connection connection = getConnection();

        try {


            String fax = faxT;
            Statement statement = connection.createStatement();
            String fp = "880";
            String s = fax.substring(1,2);
            String f = fax.substring(3,10);

            String query = String.format("insert into fax values(%s, %s, %s, %d)",
                    fp,s,f,id);

            statement.executeUpdate(query);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
