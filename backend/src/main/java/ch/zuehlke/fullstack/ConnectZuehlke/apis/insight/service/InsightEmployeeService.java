package ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service;

import ch.zuehlke.fullstack.ConnectZuehlke.domain.Employee;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface InsightEmployeeService {

  List<Employee> getEmployees();

  byte[] getEmployeePicture(String id) throws IOException;

  Optional<SingleEmployee> getSingleEmployee(String code);

  List<Project> getAllProjects(String code);

  Optional<Project> getCurrentProject(String code);

  List<Project> getProjectHistory(String code);
}
