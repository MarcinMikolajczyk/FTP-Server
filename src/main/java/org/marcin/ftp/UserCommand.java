package org.marcin.ftp;

final class UserCommand implements Command {

    @Override
    public String proceed(ClientHandler handler, String... args) {
        if (!args[0].equals("anonymous")) {
            handler.send("530 Only anonymous supported.\r\n".getBytes());
            System.out.printf("login %s is incorrect", args[0]);
            throw new UnknownUserException();
        }

        System.out.println("logging in user anonymous");
        handler.send("331 User name is OK, give me e-mail.\r\n".getBytes());

        return handler.receive();
    }
}
