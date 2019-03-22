package ch.zuehlke.fullstack.ConnectZuehlke.service.question;

import ch.zuehlke.fullstack.ConnectZuehlke.domain.*;
import ch.zuehlke.fullstack.ConnectZuehlke.service.question.LocationQuestionFactory;
import ch.zuehlke.fullstack.ConnectZuehlke.service.question.QuestionFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class LocationQuestionFactoryTest {

    private QuestionFactory factory;

    @Before
    public void setUp() {
        this.factory = new LocationQuestionFactory();
    }

    @Test
    public void createWithEmployeesFromTheSameOneCountry() {
        // Setup the test case
        List<Employee> employees = Arrays.asList(
                new EmployeeBuilder().setId(1).setLocation("Bern").build(),
                new EmployeeBuilder().setId(2).setLocation("Schlieren").build(),
                new EmployeeBuilder().setId(3).setLocation("Schlieren").build(),
                new EmployeeBuilder().setId(4).setLocation("Bern").build(),
                new EmployeeBuilder().setId(5).setLocation("Bern").build(),
                new EmployeeBuilder().setId(6).setLocation("Schlieren").build()
        );

        // Execute the test case
        Question actual = this.factory.create(employees);

        // Verify the test results
        assertThat(actual)
                .isNotNull();
        assertThat(actual.getAnswers())
                .hasSize(2)
                .extracting(Answer::getTitle, Answer::getMatchingEmployeeIds)
                .containsOnly(
                        tuple(Country.SWITZERLAND.getName(), set(1, 2, 3, 4, 5, 6)),
                        tuple(Country.OTHERS.getName(), set())
                );
    }

    @Test
    public void createWithEmployeesFromManyCountries() {
        // Setup the test case
        List<Employee> employees = Arrays.asList(
                new EmployeeBuilder().setId(1).setLocation("Bern").build(),
                new EmployeeBuilder().setId(2).setLocation("Schlieren").build(),
                new EmployeeBuilder().setId(3).setLocation("Schlieren").build(),
                new EmployeeBuilder().setId(4).setLocation("London").build(),
                new EmployeeBuilder().setId(5).setLocation("Hamburg").build(),
                new EmployeeBuilder().setId(6).setLocation("München").build(),
                new EmployeeBuilder().setId(7).setLocation("Manchester").build(),
                new EmployeeBuilder().setId(8).setLocation("Eschborn").build()
        );

        // Execute the test case
        Question actual = this.factory.create(employees);

        // Verify the test results
        assertThat(actual)
                .isNotNull();
        assertThat(actual.getAnswers())
                .hasSize(3)
                .extracting(Answer::getTitle, Answer::getMatchingEmployeeIds)
                .containsOnly(
                        tuple(Country.SWITZERLAND.getName(), set(1, 2, 3)),
                        tuple(Country.GERMANY.getName(), set(5, 6, 8)),
                        tuple(Country.OTHERS.getName(), set(4, 7))
                );

    }

    @Test
    public void createWithEmployeesHavingNoLocation() {
        // Setup the test case
        List<Employee> employees = Arrays.asList(
                new EmployeeBuilder().setId(1).setLocation("Bern").build(),
                new EmployeeBuilder().setId(2).build(),
                new EmployeeBuilder().setId(3).setLocation("Schlieren").build(),
                new EmployeeBuilder().setId(4).build(),
                new EmployeeBuilder().setId(5).setLocation("Bern").build(),
                new EmployeeBuilder().setId(6).setLocation("Hong Kong").build()
        );

        // Execute the test case
        Question actual = this.factory.create(employees);

        // Verify the test results
        assertThat(actual)
                .isNotNull();
        assertThat(actual.getAnswers())
                .hasSize(3)
                .extracting(Answer::getTitle, Answer::getMatchingEmployeeIds)
                .containsOnly(
                        tuple(Country.SWITZERLAND.getName(), set(1, 3, 5)),
                        tuple(Country.ASIA.getName(), set(6)),
                        tuple(Country.OTHERS.getName(), set(2, 4))
                );
    }

    @SafeVarargs
    private final <T> Set<T> set(T... values) {
        return Arrays.stream(values)
                .collect(Collectors.toSet());
    }

}