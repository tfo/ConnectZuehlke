package ch.zuehlke.fullstack.ConnectZuehlke.service;

import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.InsightEmployeeService;
import ch.zuehlke.fullstack.ConnectZuehlke.domain.Employee;
import ch.zuehlke.fullstack.ConnectZuehlke.domain.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        List<Employee> chosenEmployees = chooseEmployees(allEmployees, numberOfEmployees);
        Employee selectedEmployee = selectEmployee(chosenEmployees);

        Game game = new Game(UUID.randomUUID().toString(), chosenEmployees, selectedEmployee);

        LOGGER.info("Created a new game '{}' with '{}' employees.", game.getId(), numberOfEmployees);

        return game;
    }

    private List<Employee> chooseEmployees(List<Employee> allEmployees, int numberOfEmployees) {
        Set<Integer> indexes = new HashSet<>(numberOfEmployees);
        while (indexes.size() < numberOfEmployees) {
            int index = (int) (Math.random() * allEmployees.size());
            if (index == allEmployees.size()) {
                index--;
            }

            indexes.add(index);
        }

        return indexes.stream()
                .map(allEmployees::get)
                .collect(Collectors.toList());
    }

    private Employee selectEmployee(List<Employee> chosenEmployees) {
        int index = (int) (Math.random() * chosenEmployees.size());
        if (index == chosenEmployees.size()) {
            index--;
        }

        return chosenEmployees.get(index);
    }
}