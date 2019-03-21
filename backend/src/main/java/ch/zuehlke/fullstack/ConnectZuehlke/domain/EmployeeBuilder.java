package ch.zuehlke.fullstack.ConnectZuehlke.domain;

public class EmployeeBuilder {
    private String firstName = "";
    private String lastName = "";
    private int id = 0;
    private String location = "";
    private String code = "";
    private int percentage = 0;
    private boolean isManagement = false;
    private boolean isPassionated = false;
    private boolean flexpay = false;
    private int experience = 0;
    private int skillProfileCompleteness = 0;
    private String entryDate = "";
    private Grade grade = null;
    private int gender = 0;
    private String privateAddressCity = "";
    private int bankHours = 0;

    public EmployeeBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public EmployeeBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public EmployeeBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public EmployeeBuilder setLocation(String location) {
        this.location = location;
        return this;
    }

    public EmployeeBuilder setCode(String code) {
        this.code = code.toLowerCase();
        return this;
    }

    public EmployeeBuilder setPercentage(int percentage) {
        this.percentage = percentage;
        return this;
    }

    public EmployeeBuilder setIsManagement(boolean isManagement) {
        this.isManagement = isManagement;
        return this;
    }

    public EmployeeBuilder setIsPassionated(boolean isPassionated) {
        this.isPassionated = isPassionated;
        return this;
    }

    public EmployeeBuilder setFlexpay(boolean flexpay) {
        this.flexpay = flexpay;
        return this;
    }

    public EmployeeBuilder setExperience(int experience) {
        this.experience = experience;
        return this;
    }

    public EmployeeBuilder setSkillProfileCompleteness(int skillProfileCompleteness) {
        this.skillProfileCompleteness = skillProfileCompleteness;
        return this;
    }

    public EmployeeBuilder setEntryDate(String entryDate) {
        this.entryDate = entryDate;
        return this;
    }

    public EmployeeBuilder setGrade(String grade) {
        this.grade = Grade.forGrade(grade);
        return this;
    }

    public EmployeeBuilder setGender(int gender) {
        this.gender = gender;
        return this;
    }

    public EmployeeBuilder setPrivateAddressCity(String privateAddressCity) {
        this.privateAddressCity = privateAddressCity;
        return this;
    }

    public EmployeeBuilder setBankHours(int bankHours) {
        this.bankHours = bankHours;
        return this;
    }

    public Employee createEmployee() {
        return new Employee(firstName, lastName, id, code, location, percentage, isManagement, isPassionated, flexpay, experience, skillProfileCompleteness, entryDate, grade, gender, privateAddressCity, bankHours);
    }
}