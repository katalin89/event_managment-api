package ro.mycode.event_managmentapi.exceptions;

import static ro.mycode.event_managmentapi.constants.Constants.EMPTY_DATABASE_EXCEPTION;

public class ExceptionEventDbEmpty extends RuntimeException{
    public ExceptionEventDbEmpty (){
        super(EMPTY_DATABASE_EXCEPTION);
    }

}
