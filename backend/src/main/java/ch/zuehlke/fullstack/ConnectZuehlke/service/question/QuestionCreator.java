package ch.zuehlke.fullstack.ConnectZuehlke.service.question;

import ch.zuehlke.fullstack.ConnectZuehlke.domain.Employee;
import ch.zuehlke.fullstack.ConnectZuehlke.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionCreator {

    private final Collection<QuestionFactory> questionFactories;

    @Autowired
    public QuestionCreator(Collection<QuestionFactory> questionFactories) {
        this.questionFactories = questionFactories;
    }

    public List<Question> create(List<Employee> employees) {
        return this.questionFactories
                .stream()
                .map(questionFactory -> questionFactory.create(employees))
                .collect(Collectors.toList());
    }
}
