package ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service;

import ch.zuehlke.fullstack.ConnectZuehlke.domain.Employee;
import ch.zuehlke.fullstack.ConnectZuehlke.domain.EmployeeBuilder;
import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;

@Service
@Profile({"ci", "default"})
public class InsightEmployeeServiceMocked implements InsightEmployeeService {

    private static final List<Employee> EMPLOYEES = asList(
            new EmployeeBuilder().setFirstName("Klaus").setLastName("Mustermann").setId(1).setLocation("kmu").build(),
            new EmployeeBuilder().setFirstName("Magda").setLastName("Müller").setId(2).setLocation("mmu").build(),
            new EmployeeBuilder().setFirstName("Kurt").setLastName("Peters").setId(3).setLocation("kpe").build()
    );

    public List<Employee> getEmployees() {
        return EMPLOYEES;
    }

    @Override
    public byte[] getEmployeePicture(String id) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("demo_picture.jpg");
        return IOUtils.toByteArray(classPathResource.getInputStream());
    }

    @Override
    public Optional<SingleEmployee> getSingleEmployee(String code) {
        return Optional.empty();
    }

    @Override
    public List<Project> getAllProjects(String code) {
        return Collections.emptyList();
    }

    @Override
    public Optional<Project> getCurrentProject(String code) {
        return Optional.empty();
    }

    @Override
    public List<Project> getProjectHistory(String code) {
        return Collections.emptyList();
    }
}
