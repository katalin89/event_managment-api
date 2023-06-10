package ro.mycode.event_managmentapi.exceptions;

import static ro.mycode.event_managmentapi.constants.Constants.NOT_YOUR_EVENT_EXCEPTION;

public class NotYourEventException extends RuntimeException{
    public NotYourEventException(){
        super(NOT_YOUR_EVENT_EXCEPTION);
    }
}
