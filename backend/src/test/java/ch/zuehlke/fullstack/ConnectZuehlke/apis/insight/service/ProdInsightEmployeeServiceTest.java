package ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service;

import ch.zuehlke.fullstack.ConnectZuehlke.domain.Employee;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {"insight.authentication.username=test", "insight.authentication.password=ImJustFake"})
@ActiveProfiles("prod")
@Ignore
public class ProdInsightEmployeeServiceTest {

    @Autowired
    private InsightEmployeeService employeeService;

    @Test
    public void testDefaultProfileReturnsMockedInsightEmployeeService() {
        assertTrue(employeeService instanceof InsightEmployeeServiceRemote);
    }

    @Test
    public void getEmployees_getOver900_success() {
        List<Employee> employees = employeeService.getEmployees();

        Assert.assertTrue(employees.size() > 900);
    }
}