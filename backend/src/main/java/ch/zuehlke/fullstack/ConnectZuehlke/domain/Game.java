package ch.zuehlke.fullstack.ConnectZuehlke.domain;

import java.util.List;

public class Game {

    private final String id;
    private final List<Employee> employees;
    private final Employee selectedEmployee;
    private final List<Question> questions;

    public Game(String id, List<Employee> employees, Employee selectedEmployee) {
        this.id = id;
        this.employees = employees;
        this.selectedEmployee = selectedEmployee;
        this.questions = new QuestionCreator(employees, selectedEmployee).getQuestions();
    }

    public Employee getSelectedEmployee() {
        return selectedEmployee;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public String getId() {
        return id;
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
