package ch.zuehlke.fullstack.ConnectZuehlke.apis.genderize.service;

import ch.zuehlke.fullstack.ConnectZuehlke.apis.genderize.dto.GenderDto;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface GenderizeService {

    Optional<GenderDto> loadGender(String firstName);

    Map<String, GenderDto> loadGenders(Set<String> firstNames);
}
