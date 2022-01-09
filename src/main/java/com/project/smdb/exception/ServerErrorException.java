package com.project.smdb.exception;

public class ServerErrorException extends RuntimeException {
    public ServerErrorException() {
        super("Something wrong happened. Try again later");
    }
}
