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
public class FlexPayQuestionFactory implements QuestionFactory {
    @Override
    public Question create(List<Employee> employees) {
        Set<Integer> flexPayerIds = employees.stream()
                .filter(Employee::isFlexpay)
                .map(Employee::getId)
                .collect(Collectors.toSet());

        Set<Integer> fixPayerIds = employees.stream()
                .filter(employee -> !employee.isFlexpay())
                .map(Employee::getId)
                .collect(Collectors.toSet());

        return new Question(generateId(), "The person has ...?", Arrays.asList(
                new Answer(generateId(), "flex salary", flexPayerIds, "The secret person has a flexible salary."),
                new Answer(generateId(), "fix salary", fixPayerIds, "The secret person has a fixed salary.")
        ));
    }
}
