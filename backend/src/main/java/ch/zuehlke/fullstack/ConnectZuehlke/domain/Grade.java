package ch.zuehlke.fullstack.ConnectZuehlke.domain;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Objects;

public enum Grade {

    A(GradeType.MANAGEMENT),
    B(GradeType.LEAD),
    C(GradeType.LEAD),
    D(GradeType.NORMAL),
    E(GradeType.NORMAL),
    F(GradeType.NORMAL),
    G(GradeType.NORMAL);

    private final GradeType type;

    Grade(GradeType type) {
        this.type = type;
    }

    public boolean isManagement() {
        return GradeType.MANAGEMENT.equals(type);
    }

    public boolean isLead() {
        return GradeType.LEAD.equals(type);
    }

    public boolean isNormalEmployee() {
        return GradeType.NORMAL.equals(type);
    }

    public static Grade forGrade(String name) {
        return Arrays.stream(Grade.values())
                .filter(grade -> Objects.equals(grade.name().toLowerCase(), name.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(MessageFormat.format("Unsupported grade ''{0}''!", name)));
    }
}
