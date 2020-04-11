package fr.devops.Exceptions;

public class LabelNotFoundException extends Exception {
    
    public LabelNotFoundException(String message) {
        super("label not found : "+message);
    }
}
