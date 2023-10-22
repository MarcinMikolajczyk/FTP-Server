package org.marcin.ftp;

final class SystCommand implements Command {

    @Override
    public String proceed(ClientHandler handler, String... args) {
        handler.send("215 UNIX Type: L8\r\n".getBytes());
        return handler.receive();
    }
}
