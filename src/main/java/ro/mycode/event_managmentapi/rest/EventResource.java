package ro.mycode.event_managmentapi.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/event")
@CrossOrigin
public class EventResource {

    private    EventService eventService;
}
