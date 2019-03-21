package ch.zuehlke.fullstack.ConnectZuehlke.service;

import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.InsightEmployeeService;
import ch.zuehlke.fullstack.ConnectZuehlke.domain.Employee;
import ch.zuehlke.fullstack.ConnectZuehlke.domain.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class GameService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameService.class);

    private final InsightEmployeeService employeeService;

    @Autowired
    public GameService(InsightEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Game createGame(int numberOfEmployees) {
        if (numberOfEmployees <= 4) {
            throw new IllegalArgumentException("Cannot create a new game with less than four employees!");
        }

        List<Employee> allEmployees = this.employeeService.getEmployees();
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
