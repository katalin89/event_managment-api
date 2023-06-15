package ro.mycode.event_managmentapi.service;

import ro.mycode.event_managmentapi.exceptions.EventNotFoundException;
import ro.mycode.event_managmentapi.repository.EventRepo;

import javax.transaction.Transactional;

public class EventService {

    private EventRepo eventRepo;

    public EventService(EventRepo eventRepo){
        this.eventRepo=eventRepo;
    }

    @Transactional
    public  void update(UpdateDto eventRepo)throws EventNotFoundException {

    }

}
