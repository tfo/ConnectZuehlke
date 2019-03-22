package ch.zuehlke.fullstack.ConnectZuehlke.domain.question;

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
                new Answer(generateId(), "Management", managementMatchingEmployeeIds, "The secret person works in the management (Grade A)."),
                new Answer(generateId(), "Lead", leadMatchingEmployeeIds, "The secret person works as lead (Grade B, C)."),
                new Answer(generateId(), "Normal", normalMatchingEmployeeIds, "The secret person works as normal employee (Grade D - G).")
        ));
    }
}
