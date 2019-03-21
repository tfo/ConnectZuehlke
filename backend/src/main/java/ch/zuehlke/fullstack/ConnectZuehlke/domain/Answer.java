package ch.zuehlke.fullstack.ConnectZuehlke.domain;

import java.util.List;
import java.util.Objects;

public class Answer {

    private final String id;
    private final String title;
    private final List<Integer> ids;

    public Answer(String id, String title, List<Integer> ids) {
        this.id = id;
        this.title = title;
        this.ids = ids;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Integer> getIds() {
        return ids;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return Objects.equals(id, answer.id) &&
                Objects.equals(title, answer.title) &&
                Objects.equals(ids, answer.ids);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
