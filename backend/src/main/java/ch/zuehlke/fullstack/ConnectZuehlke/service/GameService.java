package ch.zuehlke.fullstack.ConnectZuehlke.service;

import ch.zuehlke.fullstack.ConnectZuehlke.apis.genderize.dto.GenderDto;
import ch.zuehlke.fullstack.ConnectZuehlke.apis.genderize.service.GenderizeService;
import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.InsightEmployeeService;
import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.Project;
import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.SingleEmployee;
import ch.zuehlke.fullstack.ConnectZuehlke.domain.Employee;
import ch.zuehlke.fullstack.ConnectZuehlke.domain.Game;
import ch.zuehlke.fullstack.ConnectZuehlke.domain.Question;
import ch.zuehlke.fullstack.ConnectZuehlke.service.fact.FunFactGenerator;
import ch.zuehlke.fullstack.ConnectZuehlke.service.question.QuestionCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameService.class);

    private final InsightEmployeeService employeeService;
    private final GenderizeService genderizeService;
    private final QuestionCreator questionCreator;
    private final FunFactGenerator funFactGenerator;

    @Autowired
    public GameService(InsightEmployeeService employeeService,
                       GenderizeService genderizeService,
                       QuestionCreator questionCreator, FunFactGenerator funFactGenerator) {
        this.employeeService = employeeService;
        this.genderizeService = genderizeService;
        this.questionCreator = questionCreator;
        this.funFactGenerator = funFactGenerator;
    }

    private static final List<String> profilesWithoutPicture = Arrays.asList(
            "dasu", "sezu", "sigl", "mibi", "frae", "pali", "juja", "stam", "brma", "kema", "sesz", "koal", "milk", "dmmo", "hawa", "vybe", "algr", "maas", "urmi", "kean", "midi", "pane", "ruvi", "chey", "nidu", "luvb", "domg", "jawy", "sebu", "mana", "jodi", "jaha", "toro", "acif", "nikw", "rojo", "dach", "tmp_insight_api", "paam", "hith", "javo", "nakh", "milm", "albr", "qych", "xdwu", "dhja", "grub", "mapr", "lepe", "anap", "rugu", "chbr", "redg", "domo", "atan", "joek", "tohe", "mapa", "freh", "nibu", "jaka", "jazz", "oldi", "hohe", "alme", "pawi", "jegr", "sahi", "sepe", "stpa", "sils", "adsp", "resc", "noro"
    );

    public Game createGame(int numberOfEmployees) {
        if (numberOfEmployees <= 4) {
            throw new IllegalArgumentException("Cannot create a new game with less than four employees!");
        }

        List<Employee> allEmployees = this.employeeService.getEmployees();
        overrideGender(allEmployees);
        allEmployees.removeIf(employee -> profilesWithoutPicture.contains(employee.getCode()));

        Game game = null;
        boolean foundUniqueSolution = false;

        while (!foundUniqueSolution) {
            List<Employee> chosenEmployees = chooseEmployees(allEmployees, numberOfEmployees);
            Employee selectedEmployee = selectEmployee(chosenEmployees);
            Optional<SingleEmployee> selectedSingleEmployee = this.employeeService.getSingleEmployee(selectedEmployee.getCode());
            List<Project> projects = this.employeeService.getAllProjects(selectedEmployee.getCode());

            List<Question> questions = this.questionCreator.create(chosenEmployees);
            String funFact = this.funFactGenerator.generate(selectedSingleEmployee.orElse(null), projects);

            game = new Game(UUID.randomUUID().toString(), chosenEmployees, selectedEmployee,
                    questions,
                    funFact);

            foundUniqueSolution = game.hasUniqueSolution();
        }

        LOGGER.info("Created a new game '{}' with '{}' employees.", game.getId(), numberOfEmployees);

        return game;
    }

//    private void overrideGender(List<Employee> allEmployees) {
//        for (Employee employee : allEmployees) {
//            if (swappedMaleToFemale.contains(employee.getCode())) {
//                employee.setGender(1);
//            }
//        }
//    }

    private void overrideGender(List<Employee> allEmployees) {
        for (Employee employee : allEmployees) {
            if (employee.getGender() > 0) {
                continue; //skip if it's not default male
            }

            Optional<GenderDto> optional = this.genderizeService.loadGender(employee.getFirstName());
            if (optional.isPresent()) {
                GenderDto gender = optional.get();
                if (gender.getGender() != null) {
                    adaptGenderBasedOnGenderizeApi(employee, gender);
                }
            }
        }
    }

    private void adaptGenderBasedOnGenderizeApi(Employee employee, GenderDto gender) {
        int genderInt = gender.isFemale() ? 1 : 0;
        if (genderInt != employee.getGender()) {
            LOGGER.info("Detect a mismatching gender for employee '{} {} ({})' found (Insight: {}, Genderize.io: {}/{}).",
                    employee.getFirstName(), employee.getLastName(), employee.getCode(), employee.getGender(),
                    gender.getGender(), gender.getProbability());
            employee.setGender(genderInt);
        }
    }

    private List<Employee> chooseEmployees(List<Employee> allEmployees, int numberOfEmployees) {
        Collections.shuffle(allEmployees);
        return allEmployees.subList(0, numberOfEmployees);
    }

    private Employee selectEmployee(List<Employee> chosenEmployees) {
        int index = (int) (Math.random() * chosenEmployees.size());
        if (index == chosenEmployees.size()) {
            index--;
        }

        return chosenEmployees.get(index);
    }
}
