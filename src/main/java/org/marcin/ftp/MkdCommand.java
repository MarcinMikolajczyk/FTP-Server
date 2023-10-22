package org.marcin.ftp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.lang.String.format;

final class MkdCommand implements Command {
    @Override
    public String proceed(ClientHandler handler, String... args) {
        try {
            Files.createDirectory(Paths.get(format("%s/%s", handler.getRootCatalog(), args[0])));
            handler.send(format("257 %s/%s directory created\r\n", handler.getRootCatalog(), args[0]).getBytes());
        } catch (IOException e) {
            throw new DirectoryCreateException(args[0]);
        }

        return handler.receive();
    }
}
