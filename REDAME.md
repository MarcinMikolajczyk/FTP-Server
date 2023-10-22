## Basic FTP Server

FTP server written in java 20. It support PASS, SYST, PWD, TYPE, PASV, LIST, CWD, MKD, MKD, MKD, CDUP, RETR commands.
Server supports only anonymous user login.


## Parameters

- port
- root catalog
- thread pool size
- lock root (the client will only be able to operate on specific files and directories of the root directory)