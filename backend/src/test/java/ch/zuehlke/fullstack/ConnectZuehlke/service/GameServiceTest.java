package ch.zuehlke.fullstack.ConnectZuehlke.service;

import ch.zuehlke.fullstack.ConnectZuehlke.apis.genderize.service.MockedGenderizeService;
import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.InsightEmployeeService;
import ch.zuehlke.fullstack.ConnectZuehlke.domain.Employee;
import ch.zuehlke.fullstack.ConnectZuehlke.domain.EmployeeBuilder;
import ch.zuehlke.fullstack.ConnectZuehlke.domain.Game;
import ch.zuehlke.fullstack.ConnectZuehlke.service.fact.FunFactGenerator;
import ch.zuehlke.fullstack.ConnectZuehlke.service.question.QuestionCreator;
import ch.zuehlke.fullstack.ConnectZuehlke.service.question.GenderQuestionFactory;
import ch.zuehlke.fullstack.ConnectZuehlke.service.question.GradeQuestionFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameServiceTest {

    public static final List<Employee> EMPLOYEES = Arrays.asList(
            new EmployeeBuilder()
                    .setFirstName("Linda")
                    .setLastName("Roggenmoser")
                    .setId(1).setLocation("Bern")
                    .setCode("liro")
                    .setBankHours(125)
                    .setGender(1)
                    .setIsManagement(true)
                    .setSkillProfileCompleteness(100)
                    .setEntryDate("2019-02-18T00:00:00")
                    .setGrade("D")
                    .setIsPassionated(false)
                    .setFlexpay(false)
                    .setExperience(0)
                    .setPrivateAddressCity("")
                    .setPercentage(100)
                    .build(),
            new EmployeeBuilder()
                    .setFirstName("Martha")
                    .setLastName("Breitenbacher")
                    .setId(2)
                    .setLocation("Schlieren")
                    .setCode("mab")
                    .setBankHours(13)
                    .setGender(1)
                    .setIsManagement(false)
                    .setSkillProfileCompleteness(50)
                    .setEntryDate("2018-02-01T00:00:00")
                    .setGrade("A")
                    .setIsPassionated(false)
                    .setFlexpay(true)
                    .setExperience(0)
                    .setPrivateAddressCity("")
                    .setPercentage(90)
                    .build(),
            new EmployeeBuilder()
                    .setFirstName("Mirco")
                    .setLastName("Biner")
                    .setId(3)
                    .setLocation("Schlieren")
                    .setCode("mibi")
                    .setBankHours(9999)
                    .setGender(0)
                    .setIsManagement(true)
                    .setSkillProfileCompleteness(100)
                    .setEntryDate("2000-01-18T00:00:00")
                    .setGrade("F")
                    .setIsPassionated(false)
                    .setFlexpay(false)
                    .setExperience(0)
                    .setPrivateAddressCity("")
                    .setPercentage(95)
                    .build(),
            new EmployeeBuilder()
                    .setFirstName("Francisco")
                    .setLastName("Perez")
                    .setId(4)
                    .setLocation("London")
                    .setCode("fep")
                    .setBankHours(0)
                    .setGender(0)
                    .setIsManagement(false)
                    .setSkillProfileCompleteness(90)
                    .setEntryDate("2015-12-01T00:00:00")
                    .setGrade("C")
                    .setIsPassionated(true)
                    .setFlexpay(false)
                    .setExperience(0)
                    .setPrivateAddressCity("")
                    .setPercentage(100)
                    .build(),
            new EmployeeBuilder()
                    .setFirstName("Hendrik")
                    .setLastName("Mustermann")
                    .setId(5)
                    .setLocation("Hamburg")
                    .setCode("hem")
                    .setBankHours(999)
                    .setGender(0)
                    .setIsManagement(false)
                    .setSkillProfileCompleteness(0)
                    .setEntryDate("2017-05-25T00:00:00")
                    .setGrade("D")
                    .setIsPassionated(false)
                    .setFlexpay(false)
                    .setExperience(0)
                    .setPrivateAddressCity("")
                    .setPercentage(90)
                    .build(),
            new EmployeeBuilder()
                    .setFirstName("Gerda")
                    .setLastName("Lendl")
                    .setId(6)
                    .setLocation("München")
                    .setCode("gele")
                    .setBankHours(13)
                    .setGender(1)
                    .setIsManagement(true)
                    .setSkillProfileCompleteness(70)
                    .setEntryDate("2017-10-12T00:00:00")
                    .setGrade("D")
                    .setIsPassionated(false)
                    .setFlexpay(true)
                    .setExperience(0)
                    .setPrivateAddressCity("")
                    .setPercentage(60)
                    .build(),
            new EmployeeBuilder()
                    .setFirstName("Ringo")
                    .setLastName("Starr")
                    .setId(7)
                    .setLocation("Manchester")
                    .setCode("ri")
                    .setBankHours(10)
                    .setGender(0)
                    .setIsManagement(true)
                    .setSkillProfileCompleteness(100)
                    .setEntryDate("1995-02-01T00:00:00")
                    .setGrade("B")
                    .setIsPassionated(false)
                    .setFlexpay(true)
                    .setExperience(0)
                    .setPrivateAddressCity("")
                    .setPercentage(100)
                    .build(),
            new EmployeeBuilder()
                    .setFirstName("Markus")
                    .setLastName("Held")
                    .setId(8)
                    .setLocation("Eschborn")
                    .setCode("mah")
                    .setBankHours(125)
                    .setGender(0)
                    .setIsManagement(true)
                    .setSkillProfileCompleteness(99)
                    .setEntryDate("2009-10-18T00:00:00")
                    .setGrade("C")
                    .setIsPassionated(false)
                    .setFlexpay(false)
                    .setExperience(0)
                    .setPrivateAddressCity("")
                    .setPercentage(100)
                    .build()
    );

    private static final QuestionCreator QUESTION_CREATOR = new QuestionCreator(Arrays.asList(
            new GenderQuestionFactory(),
            new GradeQuestionFactory()
    ));

    private GameService service;
    private InsightEmployeeService employeeService;
    private FunFactGenerator funFactGenerator;

    @Before
    public final void setUp() {
        this.employeeService = mock(InsightEmployeeService.class);
        this.funFactGenerator = mock(FunFactGenerator.class);

        this.service = new GameService(employeeService, new MockedGenderizeService(), QUESTION_CREATOR, funFactGenerator);
    }

    @Test
    public final void testCreateGame() {
        // Setup the test case
        whenGetEmployees();

        // Execute the test case
        Game actual = this.service.createGame(6);

        // Verify the test results
        assertThat(actual)
                .isNotNull();
        assertThat(actual.getEmployees())
                .hasSize(6)
                .contains(actual.getSelectedEmployee());
        assertThat(actual.getQuestions())
                .hasSizeGreaterThan(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testCreateGameWithLessThanFourEmployeesRaiseException() {
        // Execute the test case
        this.service.createGame(3);
    }

    private void whenGetEmployees() {
        when(this.employeeService.getEmployees()).thenReturn(EMPLOYEES);
    }
}