package org.marcin.ftp;

import static java.lang.String.format;

class DirectoryCreateException extends RuntimeException {
    DirectoryCreateException() {
        super("Cannot create directory");
    }

    DirectoryCreateException(String message) {
        super(format("Cannot create: %s directory", message));
    }
}
