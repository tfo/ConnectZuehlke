package ch.zuehlke.fullstack.ConnectZuehlke.domain;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Game {

    private final String id;
    private final List<Employee> employees;
    private final Employee selectedEmployee;
    private final List<Question> questions;
    private final String funFact;

    public Game(String id, List<Employee> employees, Employee selectedEmployee, List<Question> questions, String funFact) {
        this.id = id;
        this.employees = employees;
        this.selectedEmployee = selectedEmployee;
        this.questions = questions;
        this.funFact = funFact;
    }

    public boolean hasUniqueSolution() {
        Set<Integer> idsMatchingSelectedEmployee = employees.stream()
                .map(Employee::getId)
                .collect(Collectors.toSet());
        Integer selectedEmployeeId = selectedEmployee.getId();

        for (Question question : questions) {
            List<Integer> notMatchingIds = question.getAnswers().stream()
                    .filter(answer -> !answer.getMatchingEmployeeIds().contains(selectedEmployeeId))
                    .map(Answer::getMatchingEmployeeIds)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());

            idsMatchingSelectedEmployee.removeAll(notMatchingIds);
        }

        return idsMatchingSelectedEmployee.size() == 1;
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

    public String getFunFact() {
        return funFact;
    }
}
