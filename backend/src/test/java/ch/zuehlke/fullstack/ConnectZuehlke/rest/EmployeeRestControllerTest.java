package ch.zuehlke.fullstack.ConnectZuehlke.rest;

import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.InsightEmployeeService;
import ch.zuehlke.fullstack.ConnectZuehlke.domain.EmployeeBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@ActiveProfiles("default")
@WebMvcTest(value = EmployeeRestController.class)
public class EmployeeRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InsightEmployeeService employeeService;

    @Test
    public void testGetEmptyUsers() throws Exception {
        when(employeeService.getEmployees()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/employees"))
                .andExpect(content().json("[]"));

    }

    @Test
    public void testGetUsers() throws Exception {
        when(employeeService.getEmployees()).thenReturn(Collections.singletonList(new EmployeeBuilder().setFirstName("Max").setLastName("Mustermann").setCode("mmu").setId(1).setLocation("Eschborn").createEmployee()));
        mockMvc.perform(get("/api/employees"))
                .andExpect(content().json("[\n" +
                        "  {\n" +
                        "    \"firstName\": \"Max\",\n" +
                        "    \"lastName\": \"Mustermann\",\n" +
                        "    \"id\": 1,\n" +
                        "    \"code\": \"mmu\"\n" +
                        "  }\n" +
                        "]"));

    }
}

