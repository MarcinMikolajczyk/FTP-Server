package org.marcin.ftp;

import static java.lang.String.format;

final class CdupCommand implements Command {
    @Override
    public String proceed(ClientHandler handler, String... args) {
        try {
            handler.send(format("200 directory changed to %s\r\n", handler.updateDirectoryCatalog("..")).getBytes());
        } catch (IllegalDirectoryChangeException exception) {
            handler.send("550 Illegal directory change\r\n".getBytes());
        }
        return handler.receive();
    }
}
