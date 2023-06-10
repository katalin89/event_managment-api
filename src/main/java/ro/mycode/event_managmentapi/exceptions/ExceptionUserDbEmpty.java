package ro.mycode.event_managmentapi.exceptions;

import static ro.mycode.event_managmentapi.constants.Constants.USER_EMPTY_DATABASE_EXCEPTION;

public class ExceptionUserDbEmpty extends RuntimeException{
    public ExceptionUserDbEmpty(){
        super(USER_EMPTY_DATABASE_EXCEPTION);
    }
}
