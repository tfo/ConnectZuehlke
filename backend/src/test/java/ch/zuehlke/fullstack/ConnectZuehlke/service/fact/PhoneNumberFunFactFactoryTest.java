package ch.zuehlke.fullstack.ConnectZuehlke.service.fact;

import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.SingleEmployee;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class PhoneNumberFunFactFactoryTest {

    private FunFactFactory factory;

    @Before
    public void setUp() {
        this.factory = new PhoneNumberFunFactFactory();
    }

    @Test
    public void createFunFact() {
        // Setup the test case
        SingleEmployee employee = new SingleEmployee(null, false, null, null, null,
                null, "+41 79 316 4668", "+41 43 216 6453");

        // Execute the test case
        String actual = this.factory.create(employee, new ArrayList<>());

        // Verify the test results
        assertThat(actual)
                .isEqualTo("The secret person's phone number ends with 68.");
    }
}