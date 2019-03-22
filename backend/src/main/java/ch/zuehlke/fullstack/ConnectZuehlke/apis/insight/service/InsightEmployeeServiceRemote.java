package ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service;

import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto.EmployeeDto;
import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto.ProjectDto;
import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto.SingleEmployeeDto;
import ch.zuehlke.fullstack.ConnectZuehlke.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpMethod.GET;

@Service
@Profile({"prod", "staging"})
public class InsightEmployeeServiceRemote implements InsightEmployeeService {
    private final RestTemplate insightRestTemplate;

    @Autowired
    public InsightEmployeeServiceRemote(RestTemplate insightRestTemplate) {
        this.insightRestTemplate = insightRestTemplate;
    }

    @Override
    public List<Employee> getEmployees() {
        ResponseEntity<List<EmployeeDto>> response = this.insightRestTemplate
                .exchange("/employees", GET, null, new ParameterizedTypeReference<List<EmployeeDto>>() {
                });

        return mapList(response.getBody(), EmployeeDto::toEmployee);
    }

    @Override
    public byte[] getEmployeePicture(String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_OCTET_STREAM));

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<byte[]> response = this.insightRestTemplate
                .exchange("/employees/" + id + "/picture?large=false", GET, entity, byte[].class, "1");
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        throw new IllegalStateException("Status code was not 200");
    }

    @Override
    public Optional<SingleEmployee> getSingleEmployee(String code) {
        ResponseEntity<SingleEmployeeDto> response = this.insightRestTemplate
                .getForEntity("/employees/" + code, SingleEmployeeDto.class);

        if (Objects.isNull(response.getBody())) {
            return Optional.empty();
        }

        return Optional.of(response.getBody().toSingleEmployee());
    }

    @Override
    public List<Project> getAllProjects(String code) {
        List<Project> projects = new ArrayList<>();

        getCurrentProject(code).ifPresent(projects::add);
        projects.addAll(getProjectHistory(code));

        return projects;
    }

    @Override
    public Optional<Project> getCurrentProject(String code) {
        ResponseEntity<List<ProjectDto>> response = this.insightRestTemplate
                .exchange("/employees/" + code + "/projects/current", GET, null, new ParameterizedTypeReference<List<ProjectDto>>() {
                });

        if (CollectionUtils.isEmpty(response.getBody())) {
            return Optional.empty();
        }

        ProjectDto projectDto = response.getBody().get(0);
        return Optional.of(projectDto.toProject());
    }

    @Override
    public List<Project> getProjectHistory(String code) {
        String url = MessageFormat.format("/employees/{0}/projects/history", code);

        ResponseEntity<List<ProjectDto>> response = this.insightRestTemplate
                .exchange(url, GET, null, new ParameterizedTypeReference<List<ProjectDto>>() {
                });

        List<ProjectDto> body = response.getBody();
        if (CollectionUtils.isEmpty(body)) {
            return Collections.emptyList();
        }

        return body.stream()
                .filter(dto -> Objects.nonNull(dto.getCustomerName()))
                .map(ProjectDto::toProject)
                .collect(toList());
    }

    private <T, R> List<R> mapList(List<T> list, Function<? super T, ? extends R> mapper) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }

        return list.stream()
                .map(mapper)
                .collect(toList());
    }
}
