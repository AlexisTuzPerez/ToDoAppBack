package com.toDoApp.exceptions.customExceptions;




public class ForbiddenException  extends RuntimeException{


    public ForbiddenException(String message){
        super(message);
        //the super is because message comes from RuntimeException
    }
}
