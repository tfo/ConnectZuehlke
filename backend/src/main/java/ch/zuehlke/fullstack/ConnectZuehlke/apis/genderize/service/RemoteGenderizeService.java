package ch.zuehlke.fullstack.ConnectZuehlke.apis.genderize.service;

import ch.zuehlke.fullstack.ConnectZuehlke.apis.genderize.config.GenderizeProperties;
import ch.zuehlke.fullstack.ConnectZuehlke.apis.genderize.dto.GenderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

//@Service
//@Profile({"prod", "staging"})
public class RemoteGenderizeService implements GenderizeService {

    private final RestTemplate restTemplate;

    @Autowired
    public RemoteGenderizeService(RestTemplateBuilder restTemplateBuilder, GenderizeProperties properties) {
        this.restTemplate = restTemplateBuilder
                .rootUri(properties.getUrl())
                .build();
    }

    @Override
    public Optional<GenderDto> loadGender(String firstName) {
        Objects.requireNonNull(firstName, "Unable to load the gender for first name that is 'null'!");

        return Optional.ofNullable(restTemplate.getForObject("/?name=" + firstName, GenderDto.class));
    }

    @Override
    public Map<String, GenderDto> loadGenders(Set<String> firstNames) {
        return firstNames.stream()
                .map(this::loadGender)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toMap(GenderDto::getName, Function.identity()));
    }
}
