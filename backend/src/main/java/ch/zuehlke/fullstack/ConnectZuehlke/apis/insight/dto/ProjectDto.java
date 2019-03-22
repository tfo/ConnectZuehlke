package ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashMap;
import java.util.Map;

public class ProjectDto {
    private String customerName;
    private String industry;

    @JsonProperty("Project")
    private void unpackCustomerNameFromProject(Map<Object, Object> project) {
        this.customerName = (String)((LinkedHashMap)project.get("Customer")).get("Name");
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getIndustry() {
        return industry;
    }
}
