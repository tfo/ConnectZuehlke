package ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service;

import ch.zuehlke.fullstack.ConnectZuehlke.domain.Employee;

public class SingleEmployee {
    private final boolean hasGithub;
    private String jobTitle;
    private String superiorName;
    private String zuehlkeTeamName;
    private Employee employee;

    public SingleEmployee(Employee employee, boolean hasGithub, String jobTitle, String superiorName, String zuehlkeTeamName) {
        this.employee = employee;
        this.hasGithub = hasGithub;
        this.jobTitle = jobTitle;
        this.superiorName = superiorName;
        this.zuehlkeTeamName = zuehlkeTeamName;
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

    public Employee getEmployee() {
        return employee;
    }
}
