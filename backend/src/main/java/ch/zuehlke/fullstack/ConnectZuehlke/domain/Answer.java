package ch.zuehlke.fullstack.ConnectZuehlke.domain;

import java.util.List;
import java.util.Objects;

public class Answer {

    private final String id;
    private final String title;
    private final List<Integer> matchingEmployeeIds;

    public Answer(String id, String title, List<Integer> matchingEmployeeIds) {
        this.id = id;
        this.title = title;
        this.matchingEmployeeIds = matchingEmployeeIds;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Integer> getMatchingEmployeeIds() {
        return matchingEmployeeIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return Objects.equals(id, answer.id) &&
                Objects.equals(title, answer.title) &&
                Objects.equals(matchingEmployeeIds, answer.matchingEmployeeIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

