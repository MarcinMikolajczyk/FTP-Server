package org.marcin.ftp;

import static java.lang.String.format;

class UnrecognizedCommandException extends RuntimeException {
    UnrecognizedCommandException() {
        super("Unsupported command");
    }

    UnrecognizedCommandException(String name) {
        super(format("Command: %s is unsupported", name));
    }
}
