package ro.mycode.event_managmentapi.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.mycode.event_managmentapi.dto.LoginDto;
import ro.mycode.event_managmentapi.model.User;
import ro.mycode.event_managmentapi.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/users/")
@CrossOrigin
public class UserResource {

    private UserService userService;

    private UserResource(UserService userService){
        this.userService=userService;
    }

    @GetMapping("allUsers")
    public ResponseEntity<List<User>>getAllUsers(){
        List<User>allUsers=userService.getAllUsers();
        return  new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @PostMapping("login")
    public ResponseEntity<User>getUser(@Valid @RequestBody LoginDto user){



      return new  ResponseEntity<>(userService.getUser(user),HttpStatus.OK);
    }


}
