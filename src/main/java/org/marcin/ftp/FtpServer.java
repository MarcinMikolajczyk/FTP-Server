package org.marcin.ftp;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FtpServer {

    private final ExecutorService pool;
    private final ServerSocket serverSocket;
    private final FtpParams params;

    public FtpServer(FtpParams params) throws IOException {
        serverSocket = new ServerSocket(params.port());
        pool = Executors.newFixedThreadPool(params.threadPoolSize());
        this.params = params;
    }

    public void run() throws IOException {

        while(true) {
            var connection = serverSocket.accept();

            pool.execute(() -> {
                System.out.printf("Connected: %s, port: %s%n",
                        connection.getInetAddress().getHostAddress(), connection.getPort());

                try {
                    new ClientHandler(connection, params).run();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
}
