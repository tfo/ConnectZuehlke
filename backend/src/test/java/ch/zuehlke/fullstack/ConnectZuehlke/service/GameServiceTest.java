package ch.zuehlke.fullstack.ConnectZuehlke.service;

import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.InsightEmployeeService;
import ch.zuehlke.fullstack.ConnectZuehlke.domain.Employee;
import ch.zuehlke.fullstack.ConnectZuehlke.domain.Game;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameServiceTest {

    private static final List<Employee> EMPLOYEES = Arrays.asList(
            new Employee("Linda", "Roggenmoser", 1, "Bern"),
            new Employee("Martha", "Breitenbacher", 2, "Schlieren"),
            new Employee("Mirco", "Biner", 3, "Schlieren"),
            new Employee("Francisco", "Perez", 4, "London"),
            new Employee("Hendrik", "Mustermann", 5, "Hamburg"),
            new Employee("Gerda", "Lendl", 6, "München"),
            new Employee("Ringo", "Starr", 7, "Manchester"),
            new Employee("Markus", "Held", 8, "Eschborn")
    );

    private GameService service;
    private InsightEmployeeService employeeService;

    @Before
    public final void setUp() {
        this.employeeService = mock(InsightEmployeeService.class);

        this.service = new GameService(employeeService);
    }

    @Test
    public final void testCreateGame() {
        // Setup the test case
        whenGetEmployees();

        // Execute the test case
        Game actual = this.service.createGame(6);

        // Verify the test results
        assertThat(actual)
                .isNotNull();
        assertThat(actual.getEmployees())
                .hasSize(6)
                .contains(actual.getSelectedEmployee());
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testCreateGameWithLessThanFourEmployeesRaiseException() {
        // Execute the test case
        this.service.createGame(3);
    }

    private void whenGetEmployees() {
        when(this.employeeService.getEmployees()).thenReturn(EMPLOYEES);
    }
}