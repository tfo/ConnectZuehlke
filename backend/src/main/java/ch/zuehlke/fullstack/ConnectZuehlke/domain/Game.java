package ch.zuehlke.fullstack.ConnectZuehlke.domain;

import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.SingleEmployee;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Game {

    private final String id;
    private final List<Employee> employees;
    private final SingleEmployee selectedEmployee;
    private final List<Question> questions;

    public Game(String id, List<Employee> employees, SingleEmployee selectedEmployee, List<Question> questions) {
        this.id = id;
        this.employees = employees;
        this.selectedEmployee = selectedEmployee;
        this.questions = questions;
    }

    public boolean hasUniqueSolution() {
        Set<Integer> idsMatchingSelectedEmployee = employees.stream()
                .map(Employee::getId)
                .collect(Collectors.toSet());
        Integer selectedEmployeeId = selectedEmployee.getEmployee().getId();

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

    public SingleEmployee getSelectedEmployee() {
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
