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
public class FullTimeQuestionFactory implements QuestionFactory {

    private static final int PERCENTAGE_FULL_TIME = 100;

    @Override
    public Question create(List<Employee> employees) {
        Set<Integer> yesMatchingEmployeeIds = employees.stream()
                .filter(employee -> employee.getPercentage() == PERCENTAGE_FULL_TIME)
                .map(Employee::getId)
                .collect(Collectors.toSet());
        Set<Integer> noMatchingEmployeeIds = employees.stream()
                .filter(employee -> employee.getPercentage() < PERCENTAGE_FULL_TIME)
                .map(Employee::getId)
                .collect(Collectors.toSet());

        return new Question(generateId(), "Does the person work ... for Zühlke?", Arrays.asList(
                new Answer(generateId(), "full time", yesMatchingEmployeeIds, "The secret person works full time."),
                new Answer(generateId(), "part time", noMatchingEmployeeIds, "The secret person works part time.")
        ));
    }
}
