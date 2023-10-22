package org.marcin.ftp;

import static java.lang.String.format;

class CatalogListException extends RuntimeException {
    CatalogListException(String message) {
        super(format("Cannot to list catalog, message: %s", message));
    }
}
