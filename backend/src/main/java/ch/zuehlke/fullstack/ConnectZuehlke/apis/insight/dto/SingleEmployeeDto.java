package ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto;

import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.SingleEmployee;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties
public class SingleEmployeeDto extends EmployeeDto {

    @JsonProperty("GitHubUrl")
    private String githubUrl;
    private String jobTitle;
    private String hcu;
    private String teamNameName;

    @JsonProperty("Qualification")
    private void unpackJobTitleFromQualifiction(Map<String, String> qualification) {
        jobTitle = qualification.get("Name");
        this.setGrade(qualification.get("Grade"));
    }

    @JsonProperty("Superior")
    private void unpackSuperiorNameFromSuperior(Map<Object, Object> superior) {
        hcu = superior.get("FirstName") + " " + superior.get("LastName");
    }

    @JsonProperty("Team")
    private void unpackTeamNameFromTeam(Map<String, String> team) {
        teamNameName = team.get("Name");
    }

    public SingleEmployee toSingleEmployee() {
        return new SingleEmployee(this.toEmployee(), (this.githubUrl != null), this.jobTitle, this.hcu, this.teamNameName);
    }
}
