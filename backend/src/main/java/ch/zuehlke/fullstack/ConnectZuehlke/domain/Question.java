package ch.zuehlke.fullstack.ConnectZuehlke.domain;

import java.util.List;
import java.util.Objects;

public class Question {

    private final String title;
    private final List<Answer> answers;

    public Question(String title, List<Answer> answers) {
        this.title = title;
        this.answers = answers;
    }

    public String getTitle() {
        return title;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(title, question.title) &&
                Objects.equals(answers, question.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, answers);
    }
}
