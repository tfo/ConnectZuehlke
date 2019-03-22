package ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service;

public class Project {
    private String customerName;
    private String industry;

    public Project(String customerName, String industry) {
        this.customerName = customerName;
        this.industry = industry;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getIndustry() {
        return industry;
    }
}
