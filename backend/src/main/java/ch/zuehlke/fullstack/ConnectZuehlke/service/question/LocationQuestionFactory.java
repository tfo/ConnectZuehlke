package ch.zuehlke.fullstack.ConnectZuehlke.service.question;

import ch.zuehlke.fullstack.ConnectZuehlke.domain.Answer;
import ch.zuehlke.fullstack.ConnectZuehlke.domain.Country;
import ch.zuehlke.fullstack.ConnectZuehlke.domain.Employee;
import ch.zuehlke.fullstack.ConnectZuehlke.domain.Question;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static ch.zuehlke.fullstack.ConnectZuehlke.utils.IdGenerator.generateId;

@Component
public class LocationQuestionFactory implements QuestionFactory {
    @Override
    public Question create(List<Employee> employees) {
        Set<Country> topCountries = getTopTopCountries(employees);

        Map<Country, Set<Integer>> employeePerCountry = new TreeMap<>();
        employeePerCountry.put(Country.OTHERS, new HashSet<>());

        for (Employee employee : employees) {
            Country country = toTopCountry(topCountries, employee.getCountry());

            employeePerCountry
                    .computeIfAbsent(country, key -> new HashSet<>())
                    .add(employee.getId());
        }

        List<Answer> answers = new ArrayList<>();
        for (Map.Entry<Country, Set<Integer>> entry : employeePerCountry.entrySet()) {
            Country country = entry.getKey();
            Set<Integer> matchingEmployeeIds = entry.getValue();

            answers.add(new Answer(generateId(), country.getName(), matchingEmployeeIds,
                    MessageFormat.format("The secret person works in {0}.", country.getName())));
        }

        return new Question(generateId(), "The person works in ...?", answers);
    }

    private Country toTopCountry(Set<Country> topCountries, Country country) {
        if (topCountries.contains(country)) {
            return country;
        } else {
            return Country.OTHERS;
        }
    }

    private Set<Country> getTopTopCountries(List<Employee> employees) {
        Map<Country, Long> countryOccurrence = employees.stream()
                .map(Employee::getCountry)
                .filter(country -> ObjectUtils.notEqual(Country.OTHERS, country))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return countryOccurrence.entrySet()
                .stream()
                .map(entry -> Pair.of(entry.getKey(), entry.getValue()))
                .sorted((leftEntry, rightEntry) -> new CompareToBuilder()
                        .append(rightEntry.getValue(), leftEntry.getValue())
                        .toComparison())
                .limit(2)
                .map(Pair::getKey)
                .collect(Collectors.toSet());
    }
}
