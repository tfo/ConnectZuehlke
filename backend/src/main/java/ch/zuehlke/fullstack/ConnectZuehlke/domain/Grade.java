package ch.zuehlke.fullstack.ConnectZuehlke.domain;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Objects;

public enum Grade {

    A(GradeType.TOP),
    B(GradeType.TOP),
    C(GradeType.MIDDLE),
    D(GradeType.MIDDLE),
    E(GradeType.BOTTOM),
    F(GradeType.BOTTOM),
    G(GradeType.BOTTOM);

    private final GradeType type;

    Grade(GradeType type) {
        this.type = type;
    }

    public boolean isManagement() {
        return GradeType.TOP.equals(type);
    }

    public boolean isLead() {
        return GradeType.MIDDLE.equals(type);
    }

    public boolean isNormalEmployee() {
        return GradeType.BOTTOM.equals(type);
    }

    public static Grade forGrade(String name) {
        return Arrays.stream(Grade.values())
                .filter(grade -> Objects.equals(grade.name().toLowerCase(), name.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(MessageFormat.format("Unsupported grade ''{0}''!", name)));
    }
}
