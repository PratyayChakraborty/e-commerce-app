package com.ecom.Exception;



import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDetail {

    private LocalDateTime timeStamp;
    private String mssg;
    private String description;
    public ErrorDetail(){
        this.timeStamp = LocalDateTime.now();
        
    }

    public ErrorDetail( String mssg , String description ){
        this();
        this.mssg = mssg;
        this.description = description;
    }

}

