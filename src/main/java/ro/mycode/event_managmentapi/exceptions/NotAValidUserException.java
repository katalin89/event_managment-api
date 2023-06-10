package ro.mycode.event_managmentapi.exceptions;

import static ro.mycode.event_managmentapi.constants.Constants.NOT_A_VALID_USER;

public class NotAValidUserException extends RuntimeException{

    public NotAValidUserException(){
        super(NOT_A_VALID_USER);
    }
}
