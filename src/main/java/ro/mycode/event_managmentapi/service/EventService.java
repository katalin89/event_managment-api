package ro.mycode.event_managmentapi.service;

import org.springframework.stereotype.Service;
import ro.mycode.event_managmentapi.dto.UpdateDTO;
import ro.mycode.event_managmentapi.exceptions.EventNotFoundException;
import ro.mycode.event_managmentapi.exceptions.ExceptionEventDbEmpty;
import ro.mycode.event_managmentapi.exceptions.NotYourEventException;
import ro.mycode.event_managmentapi.model.Event;
import ro.mycode.event_managmentapi.repository.EventRepo;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private EventRepo eventRepo;

    public EventService(EventRepo eventRepo){
        this.eventRepo=eventRepo;
    }

    @Transactional
    public  void update(UpdateDTO event)throws EventNotFoundException {
        Optional<Event> eventByEventId=eventRepo.findById(event.getId());

        if(eventByEventId.isEmpty()){

            throw new EventNotFoundException();

        }else if(event.getUserId()!=eventByEventId.get().getUser().getId()){
            throw  new NotYourEventException();
        }

        if(!event.getEventTitle().equals("")){

            eventByEventId.get().setEventTitle(event.getEventTitle());
        }


        if(!event.getDescription().equals("")){
            eventByEventId.get().setDescription(event.getDescription());
        }
        if(!event.getDate().equals("")){
            eventByEventId.get().setDate(event.getDate());
        }

        if(!event.getLocation().equals("")){
            eventByEventId.get().setLocation(event.getLocation());
        }
        eventRepo.saveAndFlush(eventByEventId.get());


    }

    public List<Event>getAllEvents()throws ExceptionEventDbEmpty{
        List<Event> events=eventRepo.findAll();
        if(events.size()>0){
            return events;
        }
        throw  new ExceptionEventDbEmpty();
    }

}
