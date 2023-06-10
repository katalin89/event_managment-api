package ro.mycode.event_managmentapi.exceptions;

import static ro.mycode.event_managmentapi.constants.Constants.USER_NOT_HAS_THE_EVENT;

public class UserHaveNotThatEventException extends RuntimeException{
    public UserHaveNotThatEventException(){
    super(USER_NOT_HAS_THE_EVENT);
    }
}
