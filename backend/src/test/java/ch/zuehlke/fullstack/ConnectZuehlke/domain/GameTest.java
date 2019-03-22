package ch.zuehlke.fullstack.ConnectZuehlke.domain;

import ch.zuehlke.fullstack.ConnectZuehlke.domain.question.BenchHoursQuestionFactory;
import ch.zuehlke.fullstack.ConnectZuehlke.domain.question.GenderQuestionFactory;
import ch.zuehlke.fullstack.ConnectZuehlke.domain.question.GradeQuestionFactory;
import ch.zuehlke.fullstack.ConnectZuehlke.domain.question.QuestionFactory;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static ch.zuehlke.fullstack.ConnectZuehlke.service.GameServiceTest.EMPLOYEES;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameTest {

    private static final QuestionCreator QUESTION_CREATOR = new QuestionCreator(Arrays.asList(
            new GradeQuestionFactory(),
            new BenchHoursQuestionFactory(),
            new GenderQuestionFactory()
    ));

    @Test
    public void hasUniqueSolution_withDefaultEmployees_success() {
        List<Employee> employees = EMPLOYEES;
        Game game = new Game("0", employees, employees.get(0), QUESTION_CREATOR.create(employees));

        assertTrue(game.hasUniqueSolution());
    }

    @Test
    public void hasUniqueSolution_withMatchingEmployees_noUniqueSolution() {
        Employee employee = new EmployeeBuilder().setId(0).setGrade("A").build();
        Employee clone = new EmployeeBuilder().setId(1).setGrade("A").build();
        List<Employee> employees = Arrays.asList(employee, clone);

        Game game = new Game("0", employees, employee, QUESTION_CREATOR.create(employees));

        assertFalse(game.hasUniqueSolution());
    }
}
