package org.marcin.ftp;

final class TypeCommand implements Command {
    @Override
    public String proceed(ClientHandler handler, String... args) {
        handler.send("200 Command okay.\r\n".getBytes());
        return handler.receive();
    }
}
