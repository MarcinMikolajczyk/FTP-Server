package org.marcin.ftp;

import lombok.Builder;

@Builder
public record FtpParams(int port, String root, int threadPoolSize, boolean lockRoot) {

    static FtpParams defaultParams() {
        return new FtpParams(21, "", 10, true);
    }
}
