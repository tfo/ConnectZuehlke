package ch.zuehlke.fullstack.ConnectZuehlke.domain;

import java.util.List;
import java.util.Objects;

public class Answer {

    private final String title;
    private final List<Integer> correspondingIds;

    public Answer(String title, List<Integer> correspondingIds) {
        this.title = title;
        this.correspondingIds = correspondingIds;
    }

    public String getTitle() {
        return title;
    }

    public List<Integer> getCorrespondingIds() {
        return correspondingIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return Objects.equals(title, answer.title) &&
                Objects.equals(correspondingIds, answer.correspondingIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, correspondingIds);
    }
}
