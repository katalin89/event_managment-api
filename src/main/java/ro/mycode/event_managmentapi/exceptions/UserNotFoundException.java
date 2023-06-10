package ro.mycode.event_managmentapi.exceptions;

import static ro.mycode.event_managmentapi.constants.Constants.NOT_A_VALID_USER;
import static ro.mycode.event_managmentapi.constants.Constants.USER_NOT_HAS_THE_EVENT;

public class UserNotFoundException  extends RuntimeException{
    public UserNotFoundException(){
        super(NOT_A_VALID_USER);

    }
}
