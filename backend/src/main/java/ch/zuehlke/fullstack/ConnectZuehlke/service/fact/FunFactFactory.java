package ch.zuehlke.fullstack.ConnectZuehlke.service.fact;

import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.Project;
import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.SingleEmployee;
import ch.zuehlke.fullstack.ConnectZuehlke.domain.Employee;

import java.util.List;

public interface FunFactFactory {

    String create(SingleEmployee employee, List<Project> projects);

    boolean isPossible(SingleEmployee employee, List<Project> projects);
}
