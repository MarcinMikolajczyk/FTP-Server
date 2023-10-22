package org.marcin;

import org.marcin.ftp.*;
import java.io.*;

public class Application {
    public static void main(String[] args) throws IOException {
        var ftp = new FtpServer(FtpParams.builder()
                .port(8888)
                .root("/home/marcin/ftp")
                .threadPoolSize(3)
                .lockRoot(true)
                .build());

        System.out.println("Server listen...");
        ftp.run();
    }
}


