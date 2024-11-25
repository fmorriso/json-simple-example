import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Patient {

    /////////////////////////////////////////////////////////
    // Fields
    /////////////////////////////////////////////////////////
    private String firstName;
    private String lastName;
    private ZonedDateTime dateOfBirth;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phone;

    /////////////////////////////////////////////////////////
    // Constructors
    /////////////////////////////////////////////////////////

    /**
     *
     * @param firstName
     * @param lastName
     * @param dateOfBirth
     * @param address
     * @param city
     * @param state
     * @param zip
     * @param phone
     */
    public Patient(String firstName, String lastName, String dateOfBirth, String address, String city, String state, String zip, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = DateTimeUtilities.getZonedDateTime(dateOfBirth);
        this.dateOfBirth.truncatedTo(ChronoUnit.DAYS);
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
    }

    // prevent creation of uninitialized instances
    @SuppressWarnings("unused")
    private Patient() {
    }

    /////////////////////////////////////////////////////////
    // Getters
    /////////////////////////////////////////////////////////
    public String getFirstName() {
        return firstName;
    }

    /////////////////////////////////////////////////////////
    // Setters
    /////////////////////////////////////////////////////////
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ZonedDateTime getDateOfBirth() {
        return dateOfBirth;
    }

//    @JsonIgnore()
    public String getDateOfBirthString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return dateOfBirth.format(dtf);
    }

    public void setDateOfBirth(ZonedDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /////////////////////////////////////////////////////////
    // Methods
    /////////////////////////////////////////////////////////
    public String toString() {
        StringBuilder sbr = new StringBuilder();
        sbr.append("Patient: [")

           .append("firstName: ").append(firstName).append(", ")

           .append("lastName: ").append(lastName).append(", ")

           .append("dateOfBirth: ").append(getDateOfBirthString()).append(", ")

           .append("address: ").append(address).append(", ")

           .append("city: ").append(city).append(", ")

           .append("state: ").append(state).append(", ")

           .append("zip: ").append(zip).append(", ")

           .append("phone: ").append(phone)

           .append("]");

        return sbr.toString();
    }

}
