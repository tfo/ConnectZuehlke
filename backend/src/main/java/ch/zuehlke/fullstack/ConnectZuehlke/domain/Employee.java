package ch.zuehlke.fullstack.ConnectZuehlke.domain;

public class Employee {

    private final String firstName;
    private final String lastName;
    private final int id;
    private final String code;
    private final String location;
    private final int percentage;
    private final boolean isManagement;
    private final boolean isPassionated;
    private final boolean flexpay;
    private final int experience;
    private final int skillProfileCompleteness;
    private final String entryDate;
    private final Grade grade;
    private int gender;
    private final String privateAddressCity;
    private final int bankHours;

    public Employee(String firstName, String lastName, int id, String code, String location, int percentage, boolean isManagement, boolean isPassionated, boolean flexpay, int experience, int skillProfileCompleteness, String entryDate, Grade grade, int gender, String privateAddressCity, int bankHours) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.code = code;
        this.location = location;
        this.percentage = percentage;
        this.isManagement = isManagement;
        this.isPassionated = isPassionated;
        this.flexpay = flexpay;
        this.experience = experience;
        this.skillProfileCompleteness = skillProfileCompleteness;
        this.entryDate = entryDate;
        this.grade = grade;
        this.gender = gender;
        this.privateAddressCity = privateAddressCity;
        this.bankHours = bankHours;
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

    public Country getCountry() {
        return Country.forLocation(location)
                .orElse(Country.OTHERS);
    }

    public int getPercentage() {
        return percentage;
    }

    public boolean isManagement() {
        return isManagement;
    }

    public boolean isPassionated() {
        return isPassionated;
    }

    public boolean isFlexpay() {
        return flexpay;
    }

    public int getExperience() {
        return experience;
    }

    public int getSkillProfileCompleteness() {
        return skillProfileCompleteness;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public Grade getGrade() {
        return grade;
    }

    public int getGender() {
        return gender;
    }

    public String getPrivateAddressCity() {
        return privateAddressCity;
    }

    public int getBankHours() {
        return bankHours;
    }

    //21st century
    public void setGender(int gender) {
        this.gender = gender;
    }
}
