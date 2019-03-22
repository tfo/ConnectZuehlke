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
public class SkillProfileCompletedQuestionFactory implements QuestionFactory {
    @Override
    public Question create(List<Employee> employees) {
        Set<Integer> completedIds = employees.stream()
                .filter(employee -> employee.getSkillProfileCompleteness() >= 100)
                .map(Employee::getId)
                .collect(Collectors.toSet());

        Set<Integer> notCompletedIds = employees.stream()
                .filter(employee -> employee.getSkillProfileCompleteness() < 100)
                .map(Employee::getId)
                .collect(Collectors.toSet());

        return new Question(generateId(), "Has the person ... her/his skill profile?", Arrays.asList(
                new Answer(generateId(), "completed", completedIds, "The secret person has completed her/his skill profile."),
                new Answer(generateId(), "not completed", notCompletedIds, "The secret person has not completed her/his skill profile.")
        ));
    }
}
