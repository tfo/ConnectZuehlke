package ch.zuehlke.fullstack.ConnectZuehlke.domain;

import java.util.*;
import java.util.stream.Collectors;

class QuestionCreator {

    private final List<Employee> employees;
    private final Employee selectedEmployee;
    private final List<Question> questions;

    QuestionCreator(List<Employee> employees, Employee selectedEmployee) {
        this.employees = employees;
        this.selectedEmployee = selectedEmployee;
        this.questions = new ArrayList<>();
        addQuestions();
    }

    private void addQuestions() {
        questions.add(createBankHoursQuestion());
        questions.add(createHasSkillProfileCompletedQuestion());
        questions.add(createIsManagementQuestion());
        questions.add(createSexQuestion());
        questions.add(createFlexpayQuestion());
        questions.add(createLocationQuestion());
        questions.add(createEntryDateQuestion());
        questions.add(createGradeQuestion());
    }

    private Question createBankHoursQuestion() {
        List<Integer> benchWarmerIds = employees.stream().filter(employee -> employee.getBankHours() >= 100).map(Employee::getId).collect(Collectors.toList());
        Answer benchWarmer = new Answer("0", ">= 100", benchWarmerIds);

        List<Integer> notBenchWarmerIds = employees.stream().filter(employee -> employee.getBankHours() < 100).map(Employee::getId).collect(Collectors.toList());
        Answer notBenchWarmer = new Answer("1","< 100", notBenchWarmerIds);

        return new Question("0","How many hours was the person on the bench?", Arrays.asList(benchWarmer, notBenchWarmer));
    }

    private Question createHasSkillProfileCompletedQuestion() {
        List<Integer> completedIds = employees.stream().filter(employee -> employee.getSkillProfileCompleteness() >= 100).map(Employee::getId).collect(Collectors.toList());
        Answer completed = new Answer("0","Completed", completedIds);

        List<Integer> notCompletedIds = employees.stream().filter(employee -> employee.getSkillProfileCompleteness() < 100).map(Employee::getId).collect(Collectors.toList());
        Answer notCompleted = new Answer("1","Not completed", notCompletedIds);

        return new Question("1","Has the person completed the skill profile?", Arrays.asList(completed, notCompleted));
    }

    private Question createIsManagementQuestion() {
        List<Integer> managementIds = employees.stream().filter(Employee::isManagement).map(Employee::getId).collect(Collectors.toList());
        Answer management = new Answer("0","Management", managementIds);

        List<Integer> notManagementIds = employees.stream().filter(employee -> !employee.isManagement()).map(Employee::getId).collect(Collectors.toList());
        Answer notManagement = new Answer("1","Not management", notManagementIds);

        return new Question("2","What's the role of the person?", Arrays.asList(management, notManagement));
    }

    private Question createSexQuestion() {
        List<Integer> maleIds = employees.stream().filter(employee -> employee.getGender() == 0).map(Employee::getId).collect(Collectors.toList());
        Answer male = new Answer("0","Male", maleIds);

        List<Integer> femaleIds = employees.stream().filter(employee -> employee.getGender() != 0).map(Employee::getId).collect(Collectors.toList());
        Answer female = new Answer("0","Female", femaleIds);

        return new Question("3","What's the sex of the person?", Arrays.asList(male, female));
    }

    private Question createFlexpayQuestion() {
        List<Integer> flexpayerIds = employees.stream().filter(Employee::isFlexpay).map(Employee::getId).collect(Collectors.toList());
        Answer yes = new Answer("0","Yes", flexpayerIds);

        List<Integer> notFlexpayerIds = employees.stream().filter(employee -> !employee.isFlexpay()).map(Employee::getId).collect(Collectors.toList());
        Answer no = new Answer("1","No", notFlexpayerIds);

        return new Question("4","Does the person have flexpay?", Arrays.asList(yes, no));
    }

    private Question createLocationQuestion() {
        Map<Country, List<Integer>> employeePerCountry = new HashMap<>();
        for (Employee employee : employees) {
            Country country = Country.forLocation(employee.getLocation());
            employeePerCountry
                    .computeIfAbsent(country, key -> new ArrayList<>())
                    .add(employee.getId());
        }

        List<Answer> answers = new ArrayList<>();
        for (Map.Entry<Country, List<Integer>> entry : employeePerCountry.entrySet()) {
            Country country = entry.getKey();
            List<Integer> matchingEmployeeIds = entry.getValue();

            answers.add(new Answer(UUID.randomUUID().toString(), country.getName(), matchingEmployeeIds));
        }

        return new Question("5","The person works in ...?", answers);
    }

    private static boolean isInSwitzerland(String location) {
        return location.equalsIgnoreCase("Schlieren") ||
                location.equalsIgnoreCase("Bern");
    }

    private Question createEntryDateQuestion() {
        List<Integer> oldIds = employees.stream().filter(employee -> worksForLongInZuehlke(employee.getEntryDate())).map(Employee::getId).collect(Collectors.toList());
        Answer yes = new Answer("0","Before 2017", oldIds);

        List<Integer> youngIds = employees.stream().filter(employee -> !worksForLongInZuehlke(employee.getEntryDate())).map(Employee::getId).collect(Collectors.toList());
        Answer no = new Answer("1","After 2017", youngIds);

        return new Question("6","Did the join before 2017?", Arrays.asList(yes, no));
    }

    private static boolean worksForLongInZuehlke(String entryDate) {
        if (!entryDate.contains("-")) return true;

        String[] splits = entryDate.split("-");
        Integer year = Integer.valueOf(splits[0]);
        return year <= 2017;
    }

    private Question createGradeQuestion() {
        List<Integer> highGradeIds = employees.stream().filter(employee -> hasHighGrade(employee.getGrade())).map(Employee::getId).collect(Collectors.toList());
        Answer abc = new Answer("0","A, B or C", highGradeIds);

        List<Integer> lowGradeIds = employees.stream().filter(employee -> !hasHighGrade(employee.getGrade())).map(Employee::getId).collect(Collectors.toList());
        Answer def = new Answer("1","D, E or F", lowGradeIds);

        return new Question("7","Has the person grade C or higher?", Arrays.asList(abc, def));
    }

    private static boolean hasHighGrade(String grade) {
        return  grade.toLowerCase().contains("a") ||
                grade.toLowerCase().contains("b") ||
                grade.toLowerCase().contains("c");
    }

    List<Question> getQuestions() {
        return questions;
    }
}
