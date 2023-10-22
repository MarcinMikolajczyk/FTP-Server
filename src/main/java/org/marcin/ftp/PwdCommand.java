package org.marcin.ftp;

import java.nio.charset.StandardCharsets;

import static java.lang.String.format;

final class PwdCommand implements Command {

    @Override
    public String proceed(ClientHandler handler, String... args) {
        handler.send(format("257 %s\r\n", handler.getRootCatalog()).getBytes(StandardCharsets.UTF_8));
        return handler.receive();
    }
}
