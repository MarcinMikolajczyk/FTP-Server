package org.marcin.ftp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.lang.String.format;

final class RetrCommand implements Command {

    @Override
    public String proceed(ClientHandler handler, String... args) {
        File file = new File(format("%s/%s", handler.getRootCatalog(), args[0]));

        if (!file.exists() || file.isDirectory()) {
            handler.send("550 File doesn't exist or is directory.\r\n".getBytes());
            return handler.receive();
        }

        handler.send("150 Opening IMAGE mode data connection for RETR.\r\n".getBytes());

        try {
            var socket = handler.getTransfer().accept();
            socket.getOutputStream().write(Files.readAllBytes(Path.of(file.getAbsolutePath())));
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        handler.send("226 Transfer complete\r\n".getBytes());

        return handler.receive();
    }
}
