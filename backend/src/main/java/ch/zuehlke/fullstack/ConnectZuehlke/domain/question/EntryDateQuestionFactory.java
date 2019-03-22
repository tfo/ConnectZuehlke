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
public class EntryDateQuestionFactory implements QuestionFactory {
    @Override
    public Question create(List<Employee> employees) {
        Set<Integer> oldIds = employees.stream()
                .filter(employee -> worksForLongInZuehlke(employee.getEntryDate()))
                .map(Employee::getId)
                .collect(Collectors.toSet());

        Set<Integer> youngIds = employees.stream()
                .filter(employee -> !worksForLongInZuehlke(employee.getEntryDate()))
                .map(Employee::getId)
                .collect(Collectors.toSet());

        return new Question(generateId(), "The person joined Zühlke ...?", Arrays.asList(
                new Answer(generateId(), "before 2017", oldIds, "The secret person started before 2017 at Zühlke."),
                new Answer(generateId(), "after 2017", youngIds, "The secret person started after 2017 at Zühlke.")
        ));
    }

    private static boolean worksForLongInZuehlke(String entryDate) {
        if (!entryDate.contains("-")) return true;

        String[] splits = entryDate.split("-");
        Integer year = Integer.valueOf(splits[0]);
        return year <= 2017;
    }
}
