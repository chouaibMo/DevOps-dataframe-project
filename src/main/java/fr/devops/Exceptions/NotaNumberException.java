package fr.devops.Exceptions;

public class NotaNumberException extends Exception {

    public NotaNumberException(String message) {
        super("Not a number :"+message);
    }
}


