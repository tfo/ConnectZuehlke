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
public class IsManagementQuestionFactory implements QuestionFactory {
    @Override
    public Question create(List<Employee> employees) {
        Set<Integer> managementIds = employees.stream()
                .filter(Employee::isManagement)
                .map(Employee::getId)
                .collect(Collectors.toSet());

        Set<Integer> notManagementIds = employees.stream()
                .filter(employee -> !employee.isManagement())
                .map(Employee::getId)
                .collect(Collectors.toSet());

        return new Question(generateId(), "What's the role of the person?", Arrays.asList(
                new Answer(generateId(), "Management", managementIds, "The secret person is in the Zühlke management."),
                new Answer(generateId(), "Not management", notManagementIds, "The secret person is not in the Zühlke management.")
        ));
    }
}
