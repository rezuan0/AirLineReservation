package bd.edu.seu;

public class AirLine {
    private int id;
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String state;
    private String country;
    private int postCode;
    private String fax;
    private String phone;
    private String email;

    public AirLine(int id, String firstName, String lastName, String street,
                   String city, String state, String country, int postCode) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postCode = postCode;
    }

    public AirLine(int id) {
        this.id = id;
    }

    public AirLine(String email, int id) {
        this.email = email;
        this.id = id;
    }

    public AirLine(int id, String phone) {
        this.id = id;
        this.phone = phone;
    }

    public AirLine(int id, String fName, String lName, String phone, String email) {
        this.id = id;
        this.firstName = fName;
        this.lastName = lName;
        this.phone = phone;
        this. email = email;
    }


    public String getFax() {
        return fax;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public int getPostCode() {
        return postCode;
    }

    @Override
    public String toString() {
        return "AirLine{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", postCode=" + postCode +
                '}';
    }
}
