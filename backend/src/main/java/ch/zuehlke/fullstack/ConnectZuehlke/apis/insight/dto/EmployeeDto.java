package ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto;

import ch.zuehlke.fullstack.ConnectZuehlke.domain.Employee;
import ch.zuehlke.fullstack.ConnectZuehlke.domain.EmployeeBuilder;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class EmployeeDto {
    @JsonProperty("FirstName")
    private String firstName;
    @JsonProperty("LastName")
    private String lastName;
    @JsonProperty("Code")
    private String code;
    @JsonProperty("Percentage")
    private int percentage;
    @JsonProperty("IsManagement")
    private boolean isManagement;
    @JsonProperty("IsPassionated")
    private boolean isPassionated;
    @JsonProperty("Flexpay")
    private boolean flexpay;
    @JsonProperty("Experience")
    private int experience;
    @JsonProperty("SkillProfileCompleteness")
    private int skillProfileCompleteness;
    @JsonProperty("EntryDate")
    private String entryDate;
    @JsonProperty("Grade")
    private String grade;
    @JsonProperty("Gender")
    private int gender;
    @JsonProperty("PrivateAddressCity")
    private String privateAddressCity;
    @JsonProperty("Location")
    private String location;
    @JsonProperty("BankHours")
    private int bankHours;
    @JsonProperty("Id")
    private int id;

    public Employee toEmployee() {
        return new EmployeeBuilder().setFirstName(getFirstName()).setLastName(getLastName()).setId(getId()).setCode(getCode()).setLocation(getLocation()).setPercentage(getPercentage()).setIsManagement(isManagement()).setIsPassionated(isPassionated()).setFlexpay(isFlexpay()).setExperience(getExperience()).setSkillProfileCompleteness(getSkillProfileCompleteness()).setEntryDate(getEntryDate()).setGrade(getGrade()).setGender(getGender()).setPrivateAddressCity(getPrivateAddressCity()).setBankHours(getBankHours()).build();
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

    private String getCode() {
        return code;
    }

    public String getLocation() {
        return location;
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

    public String getGrade() {
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
}
