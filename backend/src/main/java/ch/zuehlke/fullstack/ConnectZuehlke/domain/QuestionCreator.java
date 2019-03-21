package ch.zuehlke.fullstack.ConnectZuehlke.domain;

import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

class QuestionCreator {

    private static final int PERCENTAGE_FULL_TIME = 100;

    private final List<Employee> employees;
    private final Employee selectedEmployee;

    QuestionCreator(List<Employee> employees, Employee selectedEmployee) {
        this.employees = employees;
        this.selectedEmployee = selectedEmployee;
    }

    List<Question> create() {
        List<Question> questions = new ArrayList<>();

        questions.add(createFullTimeQuestion());
        questions.add(createBankHoursQuestion());
        questions.add(createHasSkillProfileCompletedQuestion());
        questions.add(createIsManagementQuestion());
        questions.add(createSexQuestion());
        questions.add(createFlexPayQuestion());
        //questions.add(createLocationQuestion());
        questions.add(createEntryDateQuestion());
        questions.add(createGradeQuestion());

        return questions;
    }

    private Question createFullTimeQuestion() {
        Set<Integer> yesMatchingEmployeeIds = this.employees.stream()
                .filter(employee -> employee.getPercentage() == PERCENTAGE_FULL_TIME)
                .map(Employee::getId)
                .collect(Collectors.toSet());
        Set<Integer> noMatchingEmployeeIds = this.employees.stream()
                .filter(employee -> employee.getPercentage() < PERCENTAGE_FULL_TIME)
                .map(Employee::getId)
                .collect(Collectors.toSet());

        return new Question(generateId(), "Does the person work ... for Zühlke?", Arrays.asList(
                new Answer(generateId(), "full time", yesMatchingEmployeeIds, "The secret person works full time."),
                new Answer(generateId(), "part time", noMatchingEmployeeIds, "The secret person works part time.")
        ));
    }

    private Question createBankHoursQuestion() {
        Set<Integer> benchWarmerIds = employees.stream()
                .filter(employee -> employee.getBankHours() >= 100)
                .map(Employee::getId)
                .collect(Collectors.toSet());

        Set<Integer> notBenchWarmerIds = employees.stream()
                .filter(employee -> employee.getBankHours() < 100)
                .map(Employee::getId)
                .collect(Collectors.toSet());

        return new Question(generateId(), "How many hours was the person on the bench?", Arrays.asList(
                new Answer(generateId(), ">= 100", benchWarmerIds, "The secret person was more than 100 hours on the bench."),
                new Answer(generateId(), "< 100", notBenchWarmerIds, "The secret person was less than 100 hours on the bench.")
        ));
    }

    private Question createHasSkillProfileCompletedQuestion() {
        Set<Integer> completedIds = employees.stream()
                .filter(employee -> employee.getSkillProfileCompleteness() >= 100)
                .map(Employee::getId)
                .collect(Collectors.toSet());

        Set<Integer> notCompletedIds = employees.stream()
                .filter(employee -> employee.getSkillProfileCompleteness() < 100)
                .map(Employee::getId)
                .collect(Collectors.toSet());

        return new Question(generateId(), "Has the person ... her/his skill profile?", Arrays.asList(
                new Answer(generateId(), "completed", completedIds, "The secret person has completed her/his skill profile."),
                new Answer(generateId(), "not completed", notCompletedIds, "The secret person has not completed her/his skill profile.")
        ));
    }

    private Question createIsManagementQuestion() {
        Set<Integer> managementIds = employees.stream()
                .filter(Employee::isManagement)
                .map(Employee::getId)
                .collect(Collectors.toSet());

        Set<Integer> notManagementIds = employees.stream()
                .filter(employee -> !employee.isManagement())
                .map(Employee::getId)
                .collect(Collectors.toSet());

        return new Question(generateId(), "What's the role of the person?", Arrays.asList(
                new Answer(generateId(), "Management", managementIds, "The secret person is in the Zühlke management."),
                new Answer(generateId(), "Not management", notManagementIds, "The secret person is not in the Zühlke management.")
        ));
    }

    private Question createSexQuestion() {
        Set<Integer> maleIds = employees.stream()
                .filter(employee -> employee.getGender() == 0)
                .map(Employee::getId)
                .collect(Collectors.toSet());

        Set<Integer> femaleIds = employees.stream()
                .filter(employee -> employee.getGender() != 0)
                .map(Employee::getId)
                .collect(Collectors.toSet());

        return new Question(generateId(), "The person is ...?", Arrays.asList(
                new Answer(generateId(), "Male", maleIds, "The secret person is male."),
                new Answer(generateId(), "Female", femaleIds, "The secret person is female.")
        ));
    }

    private Question createFlexPayQuestion() {
        Set<Integer> flexPayerIds = employees.stream()
                .filter(Employee::isFlexpay)
                .map(Employee::getId)
                .collect(Collectors.toSet());

        Set<Integer> fixPayerIds = employees.stream()
                .filter(employee -> !employee.isFlexpay())
                .map(Employee::getId)
                .collect(Collectors.toSet());

        return new Question(generateId(), "The person has ...?", Arrays.asList(
                new Answer(generateId(), "flex salary", flexPayerIds, "The secret person has a flexible salary."),
                new Answer(generateId(), "fix salary", fixPayerIds, "The secret person a fixed salary.")
        ));
    }

    private Question createLocationQuestion() {
        Map<Country, Set<Integer>> employeePerCountry = new HashMap<>();
        for (Employee employee : employees) {
            Optional<Country> optional = Country.forLocation(employee.getLocation());
            if (optional.isPresent()) {
                employeePerCountry
                        .computeIfAbsent(optional.get(), key -> new HashSet<>())
                        .add(employee.getId());
            }
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

    private Question createEntryDateQuestion() {
        Set<Integer> oldIds = employees.stream()
                .filter(employee -> worksForLongInZuehlke(employee.getEntryDate()))
                .map(Employee::getId)
                .collect(Collectors.toSet());

        Set<Integer> youngIds = employees.stream()
                .filter(employee -> !worksForLongInZuehlke(employee.getEntryDate()))
                .map(Employee::getId)
                .collect(Collectors.toSet());

        return new Question(generateId(), "The person joined Zühlke ...?", Arrays.asList(
                new Answer(generateId(), "before 2017", oldIds, "The secret person started before 2017 at Zühlke."),
                new Answer(generateId(), "after 2017", youngIds, "The secret person started after 2017 at Zühlke.")
        ));
    }

    private static boolean worksForLongInZuehlke(String entryDate) {
        if (!entryDate.contains("-")) return true;

        String[] splits = entryDate.split("-");
        Integer year = Integer.valueOf(splits[0]);
        return year <= 2017;
    }

    private Question createGradeQuestion() {
        Set<Integer> managementMatchingEmployeeIds = employees.stream()
                .filter(employee -> employee.getGrade().isManagement())
                .map(Employee::getId)
                .collect(Collectors.toSet());

        Set<Integer> leadMatchingEmployeeIds = employees.stream()
                .filter(employee -> employee.getGrade().isLead())
                .map(Employee::getId)
                .collect(Collectors.toSet());

        Set<Integer> normalMatchingEmployeeIds = employees.stream()
                .filter(employee -> employee.getGrade().isNormalEmployee())
                .map(Employee::getId)
                .collect(Collectors.toSet());

        return new Question(generateId(), "Which grade has the person?", Arrays.asList(
                new Answer(generateId(), "Management", managementMatchingEmployeeIds, "The secret person works in the management (Grade A)."),
                new Answer(generateId(), "Lead", leadMatchingEmployeeIds, "The secret person works as lead (Grade B, C)."),
                new Answer(generateId(), "Normal", normalMatchingEmployeeIds, "The secret person works as employee (Grade D - G).")
        ));
    }

    private String generateId() {
        return UUID.randomUUID().toString();
    }
}
