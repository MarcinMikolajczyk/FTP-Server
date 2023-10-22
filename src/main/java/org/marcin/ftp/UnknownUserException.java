package org.marcin.ftp;

import static java.lang.String.format;

class UnknownUserException extends RuntimeException {
    UnknownUserException() {
        super("Unknown user");
    }

    UnknownUserException(String name) {
        super(format("User: %s is unknown", name));
    }
}
