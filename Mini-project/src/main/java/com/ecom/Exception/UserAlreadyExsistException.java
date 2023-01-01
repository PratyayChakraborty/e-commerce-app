package com.ecom.Exception;

@SuppressWarnings("serial")
public class UserAlreadyExsistException extends RuntimeException {
	 public UserAlreadyExsistException( ){
	       
	    }

    public UserAlreadyExsistException( String msg){
        super(msg);
    }

}
