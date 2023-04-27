package com.example.telephonebook.util;

import com.example.telephonebook.exception.NotFoundException;

public class Validation {

    private Validation() {
        throw new IllegalStateException("Validation class");
    }

    public static void checkNotFoundWithId(boolean found, Long id) {
        checkNotFound(found, "id=" + id);
    }

    public static void checkNotFound(boolean found, String message) {
        if (!found) {
            throw new NotFoundException(message);
        }
    }

}
