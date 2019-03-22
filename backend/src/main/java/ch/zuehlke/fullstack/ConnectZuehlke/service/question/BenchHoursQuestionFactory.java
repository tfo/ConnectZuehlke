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
public class BenchHoursQuestionFactory implements QuestionFactory {
    @Override
    public Question create(List<Employee> employees) {
        Set<Integer> benchWarmerIds = employees.stream()
                .filter(employee -> employee.getBankHours() >= 100)
                .map(Employee::getId)
                .collect(Collectors.toSet());

        Set<Integer> notBenchWarmerIds = employees.stream()
                .filter(employee -> employee.getBankHours() < 100)
                .map(Employee::getId)
                .collect(Collectors.toSet());

        return new Question(generateId(), "How many hours was the person on the bench?", Arrays.asList(
                new Answer(generateId(), ">= 100", benchWarmerIds, "The secret person was more than 100 hours on the bench."),
                new Answer(generateId(), "< 100", notBenchWarmerIds, "The secret person was less than 100 hours on the bench.")
        ));
    }
}
