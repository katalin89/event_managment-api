package ro.mycode.event_managmentapi.service;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import ro.mycode.event_managmentapi.dto.AddEventRequest;
import ro.mycode.event_managmentapi.dto.LoginDTO;
import ro.mycode.event_managmentapi.dto.SignUpDTO;
import ro.mycode.event_managmentapi.exceptions.*;
import ro.mycode.event_managmentapi.model.Event;
import ro.mycode.event_managmentapi.model.User;
import ro.mycode.event_managmentapi.repository.EventRepo;
import ro.mycode.event_managmentapi.repository.UserRepo;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private EventRepo eventRepo;

    private UserRepo userRepo;

    public UserService(EventRepo eventRepo, UserRepo userRepo) {
        this.userRepo = userRepo;
        this.eventRepo = eventRepo;
    }

    @Transactional
    @Modifying
    public void addEventToUser(AddEventRequest addEventRequest) {
        if (userRepo.userHaveEvent(addEventRequest.getUserId(), addEventRequest.getEventTitle()).size() > 0) {
            throw new ExceptionUserAlreadyHasTheEvent();
        }
        Optional<User> user = userRepo.findById(addEventRequest.getUserId());
        if (user.isPresent()) {
            Event event = Event.builder()
                    .eventTitle(addEventRequest.getEventTitle())
                    .description(addEventRequest.getDescription())
                    .date(addEventRequest.getDate())
                    .location(addEventRequest.getLocation()).build();
            User user1 = user.get();
            user1.addEvent(event);
            eventRepo.saveAndFlush(event);
        } else {
            throw new UserNotFoundException();
        }
    }

    public void deleteById(Long id) throws EventNotFoundException {
        Event byId = eventRepo.findEventById(id);

        if (byId != null) {
            eventRepo.deleteById(id);
        } else {
            throw new EventNotFoundException();
        }
    }

//    @Transactional
//    @Modifying
//    public void deleteEventByEventTitle(Long userId, String eventTitle) throws EventNotFoundException {
//        Optional<Event> byTitle = eventRepo.findEventByEventTitle(eventTitle);
//        if (byTitle.get().getId() == userId) {
//            if (byTitle != null) {
//                Optional<User> optionalUser = userRepo.findById(userId);
//
//                if (optionalUser.isPresent()) {
//                    User user = optionalUser.get();
//                    if (user.exists(byTitle.get().getId())) {
//                        user.deleteEvent(byTitle.get());
//                        userRepo.saveAndFlush(user);
//                    } else {
//                        throw new UserHaveNotThatEventException();
//                    }
//
//                } else {
//                    throw new UserNotFoundException();
//                }
//            } else
//
//                throw new EventNotFoundException();
//        } else {
//            throw new UserHaveNotThatEventException();
//        }
//    }

    public void deleteEventByEventTitle(Long userId, String eventTitle) throws EventNotFoundException {
        Optional<Event> byName = eventRepo.findEventByEventTitle(eventTitle);

        if (byName.get().getId() == userId) {
            if (byName != null) {
                Optional<User> optionalUser = userRepo.findById(userId);

                if (optionalUser.isPresent()) {
                    User user = optionalUser.get();
                    if (user.exists(byName.get().getId())) {
                        user.deleteEvent(byName.get());
                        userRepo.saveAndFlush(user);
                    } else {
                        throw new UserHaveNotThatEventException();
                    }

                } else {
                    throw new UserNotFoundException();
                }
            } else
                throw new EventNotFoundException();
        } else {
            throw new UserHaveNotThatEventException();
        }
    }

    public List<String> getAllUsersName() throws ExceptionUserDbEmpty {
        List<String> users = userRepo.getAllUsersName();

        if (users.size() > 0) {
            return users;
        }
        throw new ExceptionUserDbEmpty();
    }

    public String getEvent(String eventTitle) throws ExceptionEventDbEmpty {
        String event = eventRepo.getEvent(eventTitle);
        if (event != "") {
            return event;
        }
        throw new ExceptionEventDbEmpty();
    }

    public List<Event> getAllUsersEvent(Long id) throws ExceptionEventDbEmpty {
        List<Event> events = eventRepo.getAllUsersEvent(id);

        if (events.size() > 0) {
            return events;
        }
        throw new ExceptionEventDbEmpty();
    }

    public List<Event> getAllUsersevents(Long id) {
        return userRepo.getAllUsersEvent(id);
    }

    public void addUser(User user) {
        userRepo.saveAndFlush(user);
    }


    public List<User> getAllUsers() {

        return userRepo.findAll();
    }

    public User getUser(LoginDTO loginDto) throws NotAValidUserException {
        Optional<User> user = userRepo.login(loginDto.getEmail(), loginDto.getPassword());
        if (user.isPresent()) {
            return user.get();
        }
        throw new UserNotFoundException();
    }

    public User addUserSignUp(SignUpDTO signUpDTO) throws ExistingUser {
        Optional<User> user = userRepo.signUp(signUpDTO.getFirstName(), signUpDTO.getLastName(), signUpDTO.getEmail(), signUpDTO.getPassword());

        if (!user.isPresent()) {
            User user1 = User.builder().firstName(signUpDTO.getFirstName())
                    .lastName(signUpDTO.getLastName())
                    .email(signUpDTO.getEmail())
                    .password(signUpDTO.getPassword())
                    .build();
            userRepo.saveAndFlush(user1);
            return user1;
        }
        throw new ExistingUser();

    }

//    public  void  deleteEventByEventId(Long userId) throws EventNotFoundException {
//        Optional byTitle = eventRepo.findById(userId);
//
//        if(!byTitle.isPresent()){
//            throw new EventNotFoundException();
//        }
//        else {
//            Event event=(Event)byTitle.get();
//            event.setUser(null);
//            eventRepo.saveAndFlush(event);
//        }
//    }
//

    @Transactional
    public void deleteEventByEventId(Long id) {

        Optional<Event> byName = eventRepo.findById(id);

        if(byName!=null){
            eventRepo.deleteById(id);
            eventRepo.flush();
        }else{
            throw new EventNotFoundException();
        }
    }
}
