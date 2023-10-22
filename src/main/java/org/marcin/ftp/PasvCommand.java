package org.marcin.ftp;

import static java.lang.String.format;

final class PasvCommand implements Command {
    @Override
    public String proceed(ClientHandler handler, String... args) {
        handler.send(response(handler));
        return handler.receive();
    }

    private byte[] response(ClientHandler handler) {
        return format("227 %s,%s,%s\r\n",
                extractHost(handler), extractPort(handler) / 256, extractPort(handler) % 256).getBytes();
    }

    private String extractHost(ClientHandler handler) {
        return handler.getTransfer().getInetAddress().getHostAddress().replace('.', ',');
    }

    private int extractPort(ClientHandler handler) {
        return handler.getTransfer().getLocalPort();
    }
}
