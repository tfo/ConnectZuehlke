package ch.zuehlke.fullstack.ConnectZuehlke.service.fact;

import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.Project;
import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.SingleEmployee;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.List;

@Component
public class WorkedInFunFactFactory implements FunFactFactory {

    @Override
    public String create(SingleEmployee employee, List<Project> projects) {
        Project project = chooseProject(projects);

        return MessageFormat.format("The secret person works or worked for our lovely customer {0}.", project.getCustomerName());
    }

    @Override
    public boolean isPossible(SingleEmployee employee, List<Project> projects) {
        return !projects.isEmpty();
    }

    private Project chooseProject(List<Project> projects) {
        int index = (int)(Math.random() * projects.size());
        return projects.get(index);
    }
}
