package ch.zuehlke.fullstack.ConnectZuehlke.service.question;

import ch.zuehlke.fullstack.ConnectZuehlke.domain.Answer;
import ch.zuehlke.fullstack.ConnectZuehlke.domain.Employee;
import ch.zuehlke.fullstack.ConnectZuehlke.domain.Question;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static ch.zuehlke.fullstack.ConnectZuehlke.utils.IdGenerator.generateId;

@Component
public class GradeQuestionFactory implements QuestionFactory {
    @Override
    public Question create(List<Employee> employees) {
        Set<Integer> topEmployeeIds = employees.stream()
                .filter(employee -> employee.getGrade().isManagement())
                .map(Employee::getId)
                .collect(Collectors.toSet());

        Set<Integer> middleEmployeeIds = employees.stream()
                .filter(employee -> employee.getGrade().isLead())
                .map(Employee::getId)
                .collect(Collectors.toSet());

        Set<Integer> bottomEmployeeIds = employees.stream()
                .filter(employee -> employee.getGrade().isNormalEmployee())
                .map(Employee::getId)
                .collect(Collectors.toSet());

        return new Question(generateId(), "Which grade has the person?", Arrays.asList(
                new Answer(generateId(), "A, B", topEmployeeIds, "The secret person has Grade A or B."),
                new Answer(generateId(), "C, D", middleEmployeeIds, "The secret person has Grade C or D."),
                new Answer(generateId(), "E, F, G", bottomEmployeeIds, "The secret person has Grade E, F or G.")
        ));
    }
}
