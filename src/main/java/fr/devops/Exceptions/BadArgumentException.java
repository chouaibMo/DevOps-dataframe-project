package fr.devops.Exceptions;

public class BadArgumentException extends Exception {

    public BadArgumentException(String message) {
        super("Bad argument : "+message);
    }
}

