package ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service;

public class Project {
    private String customerName;

    public Project(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }
}
