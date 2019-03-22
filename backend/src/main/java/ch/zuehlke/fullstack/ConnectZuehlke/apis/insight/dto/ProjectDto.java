package ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashMap;
import java.util.Map;

public class ProjectDto {
    private String customerName;
    private String industry;

    @JsonProperty("Project")
    private void unpackCustomerNameFromProject(Map<Object, Object> project) {
        if (project.get("Customer") != null) {
            this.customerName = (String)((LinkedHashMap)project.get("Customer")).get("Name");
        } else {
            this.customerName = "Zühlke intern";
        }
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getIndustry() {
        return industry;
    }
}
