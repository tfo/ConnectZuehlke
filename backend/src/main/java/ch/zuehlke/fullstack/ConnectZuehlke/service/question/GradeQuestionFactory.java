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
        Set<Integer> managementMatchingEmployeeIds = employees.stream()
                .filter(employee -> employee.getGrade().isManagement())
                .map(Employee::getId)
                .collect(Collectors.toSet());

        Set<Integer> leadMatchingEmployeeIds = employees.stream()
                .filter(employee -> employee.getGrade().isLead())
                .map(Employee::getId)
                .collect(Collectors.toSet());

        Set<Integer> normalMatchingEmployeeIds = employees.stream()
                .filter(employee -> employee.getGrade().isNormalEmployee())
                .map(Employee::getId)
                .collect(Collectors.toSet());

        return new Question(generateId(), "Which grade has the person?", Arrays.asList(
                new Answer(generateId(), "A", managementMatchingEmployeeIds, "The secret person has Grade A."),
                new Answer(generateId(), "B, C", leadMatchingEmployeeIds, "The secret person has Grade B or C."),
                new Answer(generateId(), "D, E, F", normalMatchingEmployeeIds, "The secret person has Grade D, E or F.")
        ));
    }
}
