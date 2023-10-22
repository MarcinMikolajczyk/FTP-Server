package org.marcin.ftp;

import com.google.common.collect.ImmutableMap;

import java.util.Map;
import java.util.Optional;

interface Command {

    Map<String, Command> commands = ImmutableMap.<String, Command>builder()
            .put("USER", new UserCommand())
            .put("PASS", new PassCommand())
            .put("SYST", new SystCommand())
            .put("PWD", new PwdCommand())
            .put("TYPE", new TypeCommand())
            .put("PASV", new PasvCommand())
            .put("LIST", new ListCommand())
            .put("CWD", new CwdCommand())
            .put("MKD", new MkdCommand())
            .put("CDUP", new CdupCommand())
            .put("RETR", new RetrCommand())
            .build();

    static Command from(String name) {
        return Optional.ofNullable(commands.get(name)).orElseGet(UnsupportedCommand::new);
    }

    String proceed(ClientHandler handler, String... args);

}
