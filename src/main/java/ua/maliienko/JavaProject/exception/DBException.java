package ua.maliienko.lab5.exception;

public class DBException extends Exception {
    public DBException(String message, Throwable cause) {
        super(message, cause);
    }
}