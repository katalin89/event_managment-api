package ro.mycode.event_managmentapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static ro.mycode.event_managmentapi.constants.Constants.EVENT_NOT_FOUND_EXCEPTION;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EventNotFoundException extends RuntimeException{

    public EventNotFoundException(){
        super(EVENT_NOT_FOUND_EXCEPTION);
    }
}
