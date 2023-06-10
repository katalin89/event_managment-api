package ro.mycode.event_managmentapi.exceptions;

import static ro.mycode.event_managmentapi.constants.Constants.EXISTING_USER;

public class ExistingUser extends RuntimeException{
    public ExistingUser(){
        super(EXISTING_USER);
    }
}
