package ch.zuehlke.fullstack.ConnectZuehlke.utils;

import java.util.UUID;

public final class IdGenerator {

    private IdGenerator() {
        // do nothing
    }

    public static String generateId() {
        return UUID.randomUUID().toString();
    }
}
