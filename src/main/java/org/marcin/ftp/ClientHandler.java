package org.marcin.ftp;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.String.format;

class ClientHandler implements Runnable {

    private final FtpParams params;
    private final BufferedReader in;
    private final OutputStream out;
    private final ServerSocket transfer;
    private String directory;

    ClientHandler(Socket connection, FtpParams params) throws IOException {
        directory = new String(params.root());
        out = connection.getOutputStream();
        in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        transfer = new ServerSocket(0, 1, InetAddress.getLocalHost());
        this.params = params;
    }

    @Override
    public void run() {
        send("220 Service ready for new user.\r\n".getBytes());
        var response = receive();

        while (true) {
            System.out.printf("command: %s\n", response);
            var resolver = ResponseResolver.from(response);

            response = Command.from(resolver.command())
                    .proceed(this, resolver.buffer());
        }
    }

    String getRootCatalog() {
        return directory;
    }

    String updateDirectoryCatalog(String path) throws IllegalDirectoryChangeException {
        var dir = calculateDirectory(path);

        if(isIllegalDirectoryChange(dir)) {
            throw new IllegalDirectoryChangeException();
        }
        directory = dir;
        return directory;
    }

    ServerSocket getTransfer() {
        return transfer;
    }

    String receive() {
        try {
            return in.readLine().strip();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void send(byte[] buff) {
        try {
            out.write(buff);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isIllegalDirectoryChange(String path) {
        return isIllegalPath(path) || isOutOfScope(path);
    }

    private boolean isOutOfScope(String path) {
        if(!params.lockRoot()) {
            return false;
        }

        return !path.startsWith(params.root());
    }

    private boolean isIllegalPath(String path) {
        return path.contains("/../") || path.contains("/..") || path.contains("../");
    }

    private String calculateDirectory(String path) {
        if (path.equals("..")) {
            return goToParentDirectory();
        } else if (new File(path).isAbsolute()) {
            return path;
        } else {
            return format("%s/%s", directory, path);
        }
    }

    private String goToParentDirectory() {
        return new File(directory).getParent();
    }
}
