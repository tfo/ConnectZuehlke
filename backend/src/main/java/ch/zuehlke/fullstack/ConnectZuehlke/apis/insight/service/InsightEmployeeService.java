package ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service;

import ch.zuehlke.fullstack.ConnectZuehlke.domain.Employee;

import java.io.IOException;
import java.util.List;

public interface InsightEmployeeService {

  List<Employee> getEmployees();

  byte[] getEmployeePicture(String id) throws IOException;

  SingleEmployee getSingleEmployee(String code);

  Project getCurrentProject(String code);
}
