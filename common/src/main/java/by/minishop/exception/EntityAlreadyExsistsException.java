package by.minishop.exception;

public class EntityAlreadyExsistsException extends RuntimeException{

    public EntityAlreadyExsistsException(String message) {
        super(message);
    }
}
