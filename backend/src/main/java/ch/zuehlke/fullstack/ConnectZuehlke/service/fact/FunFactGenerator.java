package ch.zuehlke.fullstack.ConnectZuehlke.service.fact;

import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.Project;
import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.SingleEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FunFactGenerator {

    private final List<FunFactFactory> factFactories;

    @Autowired
    public FunFactGenerator(List<FunFactFactory> factFactories) {
        this.factFactories = factFactories;
    }

    public String generate(SingleEmployee employee, List<Project> projects) {
        FunFactFactory factory = chooseFunFactFactory(employee, projects);
        return factory.create(employee, projects);
    }

    private FunFactFactory chooseFunFactFactory(SingleEmployee employee, List<Project> projects) {
        List<FunFactFactory> filteredList = this.factFactories.stream()
                .filter(factory -> factory.isPossible(employee, projects))
                .collect(Collectors.toList());

        int index = (int) (Math.random() * filteredList.size());
        return filteredList.get(index);
    }
}
