package ro.mycode.event_managmentapi.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.mycode.event_managmentapi.dto.UpdateDTO;
import ro.mycode.event_managmentapi.model.Event;
import ro.mycode.event_managmentapi.service.EventService;

import javax.validation.Valid;
import java.awt.print.Book;
import java.util.List;

@RestController
@RequestMapping("/api/v1/event")
@CrossOrigin
public class EventResource {

    private EventService eventService;

    public EventResource(EventService eventService){
        this.eventService=eventService;
    }

    @PutMapping("updateEvent")
    public ResponseEntity update(@Valid @RequestBody UpdateDTO event) {
        this.eventService.update(event);
        return  new ResponseEntity<>(HttpStatus.ACCEPTED);

    }

    @PostMapping("all")
    public ResponseEntity<List<Event>>getAllEvents(){
        List<Event>allEvents=eventService.getAllEvents();
        return new ResponseEntity<>(allEvents,HttpStatus.OK);
    }

}
