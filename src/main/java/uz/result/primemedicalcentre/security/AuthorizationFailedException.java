package uz.result.primemedicalcentre.security;

public class AuthorizationFailedException extends RuntimeException {

    public AuthorizationFailedException(String message) {
        super(message);
    }
}
