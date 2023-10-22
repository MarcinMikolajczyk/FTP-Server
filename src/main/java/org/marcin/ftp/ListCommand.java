package org.marcin.ftp;

import java.io.IOException;
import java.net.Socket;

import static java.lang.String.format;

final class ListCommand implements Command {

    @Override
    public String proceed(ClientHandler handler, String... args) {
        handler.send("150 Opening ASCII mode data connection for LIST\r\n".getBytes());

        listRoot(handler);

        handler.send("226 Transfer complete\r\n".getBytes());
        return handler.receive();
    }

    private void listRoot(ClientHandler clientHandler) {
        var transferConnection = accept(clientHandler);

        try {
            transferConnection.getOutputStream().write(response(clientHandler.getRootCatalog()));
            transferConnection.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private byte[] response(String root) {
        try {
            return format("%s\r\n", new String(
                    Runtime.getRuntime().exec(new String[]{"ls", "-l", root})
                            .getInputStream().readAllBytes())).getBytes();
        } catch (IOException exception) {
            throw new CatalogListException(exception.getMessage());
        }
    }

    private Socket accept(ClientHandler handler) {
        try {
            return handler.getTransfer().accept();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
