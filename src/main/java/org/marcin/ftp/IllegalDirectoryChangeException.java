package org.marcin.ftp;

class IllegalDirectoryChangeException extends RuntimeException {
    IllegalDirectoryChangeException() {
        super("Illegal directory change");
    }
}
