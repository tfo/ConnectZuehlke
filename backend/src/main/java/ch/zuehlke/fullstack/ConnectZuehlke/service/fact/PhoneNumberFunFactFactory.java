package ch.zuehlke.fullstack.ConnectZuehlke.service.fact;

import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.Project;
import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.SingleEmployee;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;

@Component
public class PhoneNumberFunFactFactory implements FunFactFactory {
    @Override
    public String create(SingleEmployee employee, List<Project> projects) {
        String phoneNumber = getPhoneNumber(employee);
        String endNumbers = phoneNumber.substring(phoneNumber.length() - 2);

        return MessageFormat.format("The secret person''s phone number ends with {0}.", endNumbers);
    }

    @Override
    public boolean isPossible(SingleEmployee employee, List<Project> projects) {
        return Objects.nonNull(employee) && (Objects.nonNull(employee.getMobilePhone()) || Objects.nonNull(employee.getBusinessPhone()));
    }

    private String getPhoneNumber(SingleEmployee employee) {
        if (Objects.nonNull(employee.getMobilePhone())) {
            return employee.getMobilePhone();
        } else {
            return employee.getBusinessPhone();
        }
    }
}
