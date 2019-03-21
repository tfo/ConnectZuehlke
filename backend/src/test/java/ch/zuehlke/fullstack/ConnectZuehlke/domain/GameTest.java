package ch.zuehlke.fullstack.ConnectZuehlke.domain;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static ch.zuehlke.fullstack.ConnectZuehlke.service.GameServiceTest.EMPLOYEES;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameTest {

    @Test
    public void hasUniqueSolution_withDefaultEmployees_success() {
        List<Employee> employeeList = EMPLOYEES;
        Game game = new Game("0", employeeList, employeeList.get(0));

        assertTrue(game.hasUniqueSolution());
    }

    @Test
    public void hasUniqueSolution_withMatchingEmployees_noUniqueSolution() {
        Employee employee = new EmployeeBuilder().setId(0).setGrade("A").createEmployee();
        Employee clone = new EmployeeBuilder().setId(1).setGrade("A").createEmployee();

        Game game = new Game("0", Arrays.asList(employee, clone), employee);

        assertFalse(game.hasUniqueSolution());
    }
}
