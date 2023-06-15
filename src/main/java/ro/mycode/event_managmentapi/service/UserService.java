package ro.mycode.event_managmentapi.service;

import org.springframework.stereotype.Service;
import ro.mycode.event_managmentapi.dto.LoginDto;
import ro.mycode.event_managmentapi.exceptions.NotAValidUserException;
import ro.mycode.event_managmentapi.exceptions.UserNotFoundException;
import ro.mycode.event_managmentapi.model.User;
import ro.mycode.event_managmentapi.repository.EventRepo;
import ro.mycode.event_managmentapi.repository.UserRepo;

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

    public List<User>getAllUsers(){

        return userRepo.findAll();
    }

    public User getUser(LoginDto loginDto)throws NotAValidUserException{
        Optional<User> user=userRepo.login(loginDto.getEmail(),loginDto.getPassword());
        if(user.isPresent()){
            return user.get();
        }
        throw  new UserNotFoundException();
    }

}
