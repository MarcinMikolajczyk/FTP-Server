package org.marcin.ftp;

final class UnsupportedCommand implements Command {

    @Override
    public String proceed(ClientHandler handler, String... args) {
        handler.send("500 Syntax error, command unrecognized.\r\n".getBytes());
        return handler.receive();
    }
}
