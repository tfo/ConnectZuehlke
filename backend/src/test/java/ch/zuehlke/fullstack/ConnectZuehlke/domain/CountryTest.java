package ch.zuehlke.fullstack.ConnectZuehlke.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CountryTest {

    @Test
    public final void testForLocationWithExistingLocation() {
        // Execute the test case
        Country actual = Country.forLocation("Bern");

        // Verify the test results
        assertThat(actual)
                .isEqualTo(Country.SWITZERLAND);
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testForLocationWithUnknownLocationShouldRaiseException() {
        // Execute the test case
        Country.forLocation("Prag");
    }
}