package org.marcin.ftp;

final class PassCommand implements Command {

    @Override
    public String proceed(ClientHandler handler, String... args) {
        handler.send("230 User logged in, proceed.\r\n".getBytes());
        return handler.receive();
    }
}
