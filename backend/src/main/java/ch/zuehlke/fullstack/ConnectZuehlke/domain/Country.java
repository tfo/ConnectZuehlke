package ch.zuehlke.fullstack.ConnectZuehlke.domain;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

public enum Country {
    ASIA("Asia", "Hong Kong", "Singapur", "Singapore"),
    AUSTRIA("Austria","Wien"),
    BULGARIA("Bulgaria","Sofia"),
    GERMANY("Germany","Eschborn", "München", "Hamburg", "Stuttgart", "Hannover"),
    SERBIA("Serbia", "Belgrade", "New Belgrade"),
    SWITZERLAND("Switzerland", "Bern", "Schlieren"),
    UNITED_KINGDOM("United Kingdom", "London", "Manchester");

    private final String name;
    private final List<String> locations;

    Country(String name, String... locations) {
        this.name = name;
        this.locations = Arrays.asList(locations);
    }

    public String getName() {
        return name;
    }

    public static Country forLocation(String location) {
        return Arrays.stream(Country.values())
                .filter(country -> country.locations.contains(location))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(MessageFormat.format("Unsupported location ''{0}''!", location)));
    }
}
