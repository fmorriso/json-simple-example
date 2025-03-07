import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Patient {

    private static final DateTimeFormatter DATE_OF_BIRTH_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Fields
    private String firstName;
    private String lastName;
    private ZonedDateTime dateOfBirth;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phone;


    // Constructors
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

    private Patient() { /* prevent uninitialized instances */  }

    // Getters
    public String getFirstName() {return firstName; }
    public String getLastName() {return lastName; }
    public ZonedDateTime getDateOfBirth() { return dateOfBirth; }
    public String getDateOfBirthString() { return dateOfBirth.format(DATE_OF_BIRTH_FORMATTER); }
    public String getAddress() { return address; }
    public String getCity() { return city; }
    public String getState() { return state; }
    public String getZip() {return zip;}
    public String getPhone() { return phone; }

    // Setters
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setDateOfBirth(ZonedDateTime dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public void setAddress(String address) { this.address = address; }
    public void setCity(String city) { this.city = city; }
    public void setState(String state) {this.state = state;}
    public void setZip(String zip) { this.zip = zip; }
    public void setPhone(String phone) { this.phone = phone; }


    // Methods
    @Override
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
