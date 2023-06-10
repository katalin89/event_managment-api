package ro.mycode.event_managmentapi.exceptions;

import static ro.mycode.event_managmentapi.constants.Constants.USER_ALREADY_HAS_THE_EVENT;

public class ExceptionUserAlreadyHasTheEvent extends RuntimeException {
    public ExceptionUserAlreadyHasTheEvent(){
        super(USER_ALREADY_HAS_THE_EVENT);
    }
}
