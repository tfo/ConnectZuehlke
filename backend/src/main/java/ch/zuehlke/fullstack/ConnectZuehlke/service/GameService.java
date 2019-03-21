package ch.zuehlke.fullstack.ConnectZuehlke.service;

import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.InsightEmployeeService;
import ch.zuehlke.fullstack.ConnectZuehlke.domain.Employee;
import ch.zuehlke.fullstack.ConnectZuehlke.domain.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class GameService {

    private final RestTemplate genderizeRestTemplate;
    @Value("${genderize.url}")
    private String genderizeUrl;
    private static final Logger LOGGER = LoggerFactory.getLogger(GameService.class);

    private final InsightEmployeeService employeeService;

    @Autowired
    public GameService(InsightEmployeeService employeeService,
                       RestTemplateBuilder restTemplateBuilder) {
        genderizeRestTemplate = restTemplateBuilder
                .rootUri(genderizeUrl)
                .build();
        this.employeeService = employeeService;
    }

    private static final List<String> swappedMaleToFemale = Arrays.asList(
            "sigl",
            "caas",
            "mase",
            "fras",
            "lonk",
            "osan",
            "icke",
            "allo",
            "amsa",
            "mavi",
            "juwe",
            "yuwa",
            "vifi",
            "elvu",
            "tapl",
            "stom",
            "nate",
            "vikr",
            "vemi",
            "sodo",
            "krst",
            "kean",
            "tkrs",
            "anmi",
            "susu",
            "ruvi",
            "joeh",
            "kara",
            "leba",
            "make",
            "anre",
            "acif",
            "kadi",
            "kaal",
            "vini",
            "cehe",
            "mapo",
            "kool",
            "emgr",
            "mjan",
            "stan",
            "albr",
            "chka",
            "tang",
            "insc",
            "likl",
            "isro",
            "doja",
            "ero",
            "jatr",
            "sfo",
            "marb",
            "anha",
            "toma",
            "issc",
            "sasc",
            "coeg",
            "anen",
            "aku",
            "ami",
            "fme",
            "codo",
            "sge",
            "doro",
            "piwi",
            "chh",
            "clsa",
            "hopa",
            "bekn",
            "voma",
            "alli",
            "mele",
            "lios",
            "rist",
            "pai",
            "jse",
            "dahu",
            "rapo",
            "irca",
            "clar",
            "tiko",
            "vigo",
            "mera",
            "maco",
            "kaba",
            "kwl",
            "kage",
            "anra",
            "held",
            "brad",
            "kafa",
            "anst",
            "duna",
            "ansh",
            "basc",
            "stil",
            "dats",
            "yuca",
            "asla",
            "alim",
            "suen",
            "inli",
            "jeli",
            "vga",
            "lich",
            "jpro",
            "ivdi"
    );

    public Game createGame(int numberOfEmployees) {
        if (numberOfEmployees <= 4) {
            throw new IllegalArgumentException("Cannot create a new game with less than four employees!");
        }

        List<Employee> allEmployees = this.employeeService.getEmployees();
        overrideGender(allEmployees);

        Game game = null;
        boolean foundUniqueSolution = false;

        while (!foundUniqueSolution) {
            List<Employee> chosenEmployees = chooseEmployees(allEmployees, numberOfEmployees);
            Employee selectedEmployee = selectEmployee(chosenEmployees);

            game = new Game(UUID.randomUUID().toString(), chosenEmployees, selectedEmployee);

            foundUniqueSolution = game.hasUniqueSolution();
        }

        LOGGER.info("Created a new game '{}' with '{}' employees.", game.getId(), numberOfEmployees);

        return game;
    }

    private void overrideGender(List<Employee> allEmployees) {
        for (Employee employee : allEmployees) {
            if (swappedMaleToFemale.contains(employee.getCode())) {
                employee.setGender(1);
            }
        }
    }

    /*
    private void overrideGender(List<Employee> allEmployees) {
        for (Employee employee : allEmployees) {
            if (employee.getGender() > 0) continue; //skip if it's not default male
            GenderDto gender = genderizeRestTemplate.getForObject(genderizeUrl+ "?name=" + employee.getFirstName(), GenderDto.class);
            if (gender != null && gender.getGender() != null) adaptGenderBasedOnGenderizeApi(employee, gender);
        }
    }

    private static void adaptGenderBasedOnGenderizeApi(Employee employee, GenderDto gender) {
        int genderInt = gender.getGender().equalsIgnoreCase("female") ? 1 : 0;
        if (genderInt != employee.getGender()) {
            LOGGER.info("Mismatch!: " + employee.getCode() + " " + employee.getFirstName() + " " + employee.getLastName());
            LOGGER.info("GenderDto: " + gender.getGender());
            LOGGER.info("Probability: " + gender.getProbability());
        }
    }
    */

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
