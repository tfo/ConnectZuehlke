package ch.zuehlke.fullstack.ConnectZuehlke.domain;

public class Employee {

    private final String firstName;
    private final String lastName;
    private final int id;
    private final String code;
    private final String location;

    public Employee(String firstName, String lastName, int id, String location) {
        this(firstName, lastName, id, firstName.substring(0, 1) + lastName.substring(0, 2), location);
    }

    public Employee(String firstName, String lastName, int id, String code, String location) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.code = code.toLowerCase();
        this.location = location;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getLocation() {
        return location;
    }
}
