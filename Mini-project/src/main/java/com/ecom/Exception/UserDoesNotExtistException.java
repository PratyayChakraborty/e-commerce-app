package com.ecom.Exception;

@SuppressWarnings("serial")
public class UserDoesNotExtistException extends RuntimeException{

    public UserDoesNotExtistException( String msg ){
        super(msg);
    }

}
