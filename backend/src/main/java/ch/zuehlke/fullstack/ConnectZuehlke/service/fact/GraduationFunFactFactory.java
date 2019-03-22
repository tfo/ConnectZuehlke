package ch.zuehlke.fullstack.ConnectZuehlke.service.fact;

import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.Project;
import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.SingleEmployee;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;

@Component
public class GraduationFunFactFactory implements FunFactFactory {
    @Override
    public String create(SingleEmployee employee, List<Project> projects) {
        return MessageFormat.format("The secrect person graduated as {0}.", employee.getGraduation());
    }

    @Override
    public boolean isPossible(SingleEmployee employee, List<Project> projects) {
        return Objects.nonNull(employee.getGraduation());
    }
}
