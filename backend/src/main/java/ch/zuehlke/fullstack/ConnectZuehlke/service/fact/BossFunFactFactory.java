package ch.zuehlke.fullstack.ConnectZuehlke.service.fact;

import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.Project;
import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.SingleEmployee;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;

@Component
public class BossFunFactFactory implements FunFactFactory {
    @Override
    public String create(SingleEmployee employee, List<Project> projects) {
        String superiorName = employee.getSuperiorName();

        return MessageFormat.format("{0} is the secret person''s superior.", superiorName);
    }

    @Override
    public boolean isPossible(SingleEmployee employee, List<Project> projects) {
        return Objects.nonNull(employee) && Objects.nonNull(employee.getSuperiorName());
    }
}
