package com.ecom.Exception;

@SuppressWarnings("serial")
public class LoginException extends Exception {

    public LoginException() {
    }

    public LoginException(String message) {
        super(message);
    }
}