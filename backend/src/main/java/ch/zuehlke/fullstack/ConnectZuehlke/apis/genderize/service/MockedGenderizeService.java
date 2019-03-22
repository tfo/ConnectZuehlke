package ch.zuehlke.fullstack.ConnectZuehlke.apis.genderize.service;

import ch.zuehlke.fullstack.ConnectZuehlke.apis.genderize.dto.GenderDto;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
//@Profile({"ci", "default"})
public class MockedGenderizeService implements GenderizeService {

    private static final Map<String, GenderDto> DATA = Stream.of(
            createFemale("Adriane"),
            createFemale("Aleksandra"),
            createFemale("Alexandra"),
            createFemale("Alicia"),
            createFemale("Alina"),
            createFemale("Alison"),
            createFemale("Ana"),
            createFemale("Andrea"),
            createFemale("Andreja"),
            createFemale("Anela"),
            createFemale("Anja"),
            createFemale("Angela"),
            createFemale("Anna"),
            createFemale("Annabel"),
            createFemale("Anne"),
            createFemale("Barbara"),
            createFemale("Belinda"),
            createFemale("Caroline"),
            createFemale("Celine"),
            createFemale("Christina"),
            createFemale("Claire"),
            createFemale("Claudia"),
            createFemale("Corinne"),
            createFemale("Cornelia"),
            createFemale("Daniela"),
            createFemale("Daphne"),
            createFemale("Denise"),
            createFemale("Diana"),
            createFemale("Doris"),
            createFemale("Dorota"),
            createFemale("Elena"),
            createFemale("Elisa"),
            createFemale("Emilija"),
            createFemale("Franziska"),
            createFemale("Ichrak"),
            createFemale("Ilka"),
            createFemale("Ina"),
            createFemale("Ines"),
            createFemale("Inga"),
            createFemale("Irina"),
            createFemale("Isabel"),
            createFemale("Isabelle"),
            createFemale("Ivana"),
            createFemale("Jacqueline"),
            createFemale("Jana"),
            createFemale("Jeannette"),
            createFemale("Johanna"),
            createFemale("Julia"),
            createFemale("Karen"),
            createFemale("Kateryna"),
            createFemale("Katharina"),
            createFemale("Katja"),
            createMale("Kerry"),
            createFemale("Kristina"),
            createFemale("Lena"),
            createFemale("Linda"),
            createFemale("Lisa"),
            createFemale("Lisa-Maria"),
            createFemale("Lotte"),
            createFemale("Madeleine"),
            createFemale("Maria"),
            createFemale("Marija"),
            createFemale("Marika"),
            createFemale("Marina"),
            createFemale("Marlene"),
            createFemale("Maya"),
            createFemale("Mayo"),
            createFemale("Michèle"),
            createFemale("Milica"),
            createFemale("Nada"),
            createFemale("Nazli"),
            createFemale("Noelia"),
            createFemale("Olivera"),
            createFemale("Paula"),
            createFemale("Pia"),
            createFemale("Ramona"),
            createFemale("Rita"),
            createFemale("Samirah"),
            createFemale("Sarah"),
            createFemale("Simone"),
            createFemale("Snezana"),
            createFemale("Sofija"),
            createFemale("Stefka"),
            createFemale("Stephanie"),
            createFemale("Susan"),
            createFemale("Suzana"),
            createFemale("Tamara"),
            createFemale("Tanja"),
            createFemale("Tijana"),
            createFemale("Tina"),
            createFemale("Vera"),
            createFemale("Victoria"),
            createFemale("Viktorija"),
            createFemale("Visnja"),
            createFemale("Viviane"),
            createFemale("Vivien"),
            createFemale("Yue"),
            createFemale("Yurena")
    ).collect(Collectors.toMap(GenderDto::getName, Function.identity()));

    @Override
    public Optional<GenderDto> loadGender(String firstName) {
        return Optional.ofNullable(DATA.get(firstName));
    }

    @Override
    public Map<String, GenderDto> loadGenders(Set<String> firstNames) {
        return firstNames.stream()
                .map(this::loadGender)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toMap(GenderDto::getName, Function.identity()));
    }

    private static GenderDto createFemale(String firstName) {
        return createGender(firstName, "Female");
    }

    private static GenderDto createMale(String firstName) {
        return createGender(firstName, "Male");
    }

    private static GenderDto createGender(String firstName, String gender) {
        return new GenderDto(firstName, gender, 1.0d, 2);
    }
}
