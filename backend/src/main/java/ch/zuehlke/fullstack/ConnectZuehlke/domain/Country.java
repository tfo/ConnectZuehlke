package ch.zuehlke.fullstack.ConnectZuehlke.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum Country {
    ASIA("Asia", "Hong Kong", "Singapur", "Singapore"),
    AUSTRIA("A","Wien"),
    BULGARIA("BGR","Sofia"),
    GERMANY("DE","Eschborn", "München", "Muenchen", "Hamburg", "Stuttgart", "Hannover"),
    SERBIA("SRB", "Belgrade", "New Belgrade"),
    SWITZERLAND("CH", "Bern", "Schlieren"),
    UNITED_KINGDOM("UK", "London", "Manchester"),
    OTHERS("Somewhere else");

    private final String name;
    private final List<String> locations;

    Country(String name, String... locations) {
        this.name = name;
        this.locations = Arrays.asList(locations);
    }

    public String getName() {
        return name;
    }

    public static Optional<Country> forLocation(String location) {
        return Arrays.stream(Country.values())
                .filter(country -> country.locations.contains(location))
                .findFirst();
    }
}
