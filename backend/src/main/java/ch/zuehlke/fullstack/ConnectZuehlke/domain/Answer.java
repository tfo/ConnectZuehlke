package ch.zuehlke.fullstack.ConnectZuehlke.domain;

import java.util.Objects;
import java.util.Set;

public class Answer {

    private final String id;
    private final String title;
    private final Set<Integer> matchingEmployeeIds;
    private final String fact;

    public Answer(String id, String title, Set<Integer> matchingEmployeeIds, String fact) {
        this.id = id;
        this.title = title;
        this.matchingEmployeeIds = matchingEmployeeIds;
        this.fact = fact;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Set<Integer> getMatchingEmployeeIds() {
        return matchingEmployeeIds;
    }

    public String getFact() {
        return fact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return Objects.equals(id, answer.id) &&
                Objects.equals(title, answer.title) &&
                Objects.equals(matchingEmployeeIds, answer.matchingEmployeeIds) &&
                Objects.equals(fact, answer.fact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

