package org.marcin.ftp;

record ResponseResolver(String command, String buffer) {
    static ResponseResolver from(String response) {
        var array = response.split(" ");

        if (array.length == 1)
            return new ResponseResolver(array[0], null);

        return new ResponseResolver(array[0], array[1]);
    }
}
