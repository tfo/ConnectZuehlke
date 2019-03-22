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
public class GenderQuestionFactory implements QuestionFactory {
    @Override
    public Question create(List<Employee> employees) {
        Set<Integer> maleIds = employees.stream()
                .filter(employee -> employee.getGender() == 0)
                .map(Employee::getId)
                .collect(Collectors.toSet());

        Set<Integer> femaleIds = employees.stream()
                .filter(employee -> employee.getGender() != 0)
                .map(Employee::getId)
                .collect(Collectors.toSet());

        return new Question(generateId(), "The person is ...?", Arrays.asList(
                new Answer(generateId(), "Male", maleIds, "The secret person is male."),
                new Answer(generateId(), "Female", femaleIds, "The secret person is female.")
        ));
    }
}
