package ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service;

import ch.zuehlke.fullstack.ConnectZuehlke.domain.Employee;

public class SingleEmployee {
    private final boolean hasGithub;
    private final String jobTitle;
    private final String superiorName;
    private final String zuehlkeTeamName;
    private final String graduation;
    private final String mobilePhone;
    private final String businessPhone;
    private final Employee employee;

    public SingleEmployee(Employee employee, boolean hasGithub, String jobTitle, String superiorName,
                          String zuehlkeTeamName, String graduation, String mobilePhone, String businessPhone) {
        this.employee = employee;
        this.hasGithub = hasGithub;
        this.jobTitle = jobTitle;
        this.superiorName = superiorName;
        this.zuehlkeTeamName = zuehlkeTeamName;
        this.graduation = graduation;
        this.mobilePhone = mobilePhone;
        this.businessPhone = businessPhone;
    }

    public boolean isHasGithub() {
        return hasGithub;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getSuperiorName() {
        return superiorName;
    }

    public String getZuehlkeTeamName() {
        return zuehlkeTeamName;
    }

    public String getGraduation() {
        return graduation;
    }

    public Employee getEmployee() {
        return employee;
    }

    public String getBusinessPhone() {
        return businessPhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }
}
