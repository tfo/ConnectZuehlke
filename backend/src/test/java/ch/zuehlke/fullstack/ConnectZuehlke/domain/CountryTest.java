package ch.zuehlke.fullstack.ConnectZuehlke.domain;

import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class CountryTest {

    @Test
    public final void testForLocationWithExistingLocation() {
        // Execute the test case
        Optional<Country> actual = Country.forLocation("Bern");

        // Verify the test results
        assertThat(actual)
                .isPresent()
                .contains(Country.SWITZERLAND);
    }

    @Test
    public final void testForLocationWithUnknownLocation() {
        // Execute the test case
        Optional<Country> actual = Country.forLocation("Prag");

        // Verify the test results
        assertThat(actual)
                .isNotPresent();
    }
}