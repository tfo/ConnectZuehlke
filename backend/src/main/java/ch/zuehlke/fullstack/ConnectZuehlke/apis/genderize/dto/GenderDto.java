package ch.zuehlke.fullstack.ConnectZuehlke.apis.genderize.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class GenderDto {
    @JsonProperty("name")
    private String name;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("probability")
    private double probability;
    @JsonProperty("count")
    private int count;

    //for json deserialization
    public GenderDto() {
    }

    public GenderDto(String name, String gender, double probability, int count) {
        this.name = name;
        this.gender = gender;
        this.probability = probability;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public boolean isFemale() {
        return gender.equalsIgnoreCase("female");
    }

    public double getProbability() {
        return probability;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenderDto gender1 = (GenderDto) o;
        return Double.compare(gender1.probability, probability) == 0 &&
                count == gender1.count &&
                Objects.equals(name, gender1.name) &&
                Objects.equals(gender, gender1.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gender, probability, count);
    }
}
