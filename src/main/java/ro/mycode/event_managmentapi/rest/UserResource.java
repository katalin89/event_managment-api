package ro.mycode.event_managmentapi.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.mycode.event_managmentapi.dto.AddEventRequest;
import ro.mycode.event_managmentapi.dto.LoginDTO;
import ro.mycode.event_managmentapi.dto.SignUpDTO;
import ro.mycode.event_managmentapi.model.Event;
import ro.mycode.event_managmentapi.model.User;
import ro.mycode.event_managmentapi.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

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
    @PostMapping("add")
    public  ResponseEntity addUser(@RequestBody User user){
        userService.addUser(user);
        return  new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("addEvent")
    public ResponseEntity<Void>addBook(@Valid @RequestBody AddEventRequest event){
        userService.addEventToUser(event);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

   @GetMapping("allNames")
   public ResponseEntity<List<String>>getAllUsersName(@Valid @RequestBody Long id){
        List<String>allUsersname=userService.getAllUsersName();
        return new ResponseEntity<>(allUsersname,HttpStatus.OK);
   }

   @GetMapping("allUsersEvents/{id}")
   public ResponseEntity<List<Event>>getAllUsersEvents(@PathVariable Long id) {
       List<Event> allEvents = userService.getAllUsersEvent(id);
       return new ResponseEntity<>(allEvents,HttpStatus.OK);

   }

//   @DeleteMapping("deleteEventByEventTitle/{id}")
//   ResponseEntity<String>deleteByEventTitle(@PathVariable Map<Long,String> pathVarsMap){
//        this.userService.deleteEventByEventId(Long.parseLong(pathVarsMap.get("id")));
//        return new ResponseEntity<>(pathVarsMap.get("id"),HttpStatus.ACCEPTED);
//   }

    // @PathVariable trimite prin url
    @DeleteMapping("deleteEventByEventTitle/{id}")

    ResponseEntity<String> deleteByEventTitle(@PathVariable Map<Long, String> pathVarsMap){
        this.userService.deleteEventByEventId(Long.parseLong(pathVarsMap.get("id")));
        return new ResponseEntity<>(pathVarsMap.get("id"),HttpStatus.ACCEPTED);
    }
    @PostMapping("login")
    public ResponseEntity<User>getUser(@Valid @RequestBody LoginDTO user){

      return new  ResponseEntity<>(userService.getUser(user),HttpStatus.OK);
    }

    @PostMapping("signUp")
    public ResponseEntity<User>getUserUp(@Valid @RequestBody SignUpDTO user){
        return new ResponseEntity<>(userService.addUserSignUp(user),HttpStatus.OK );
    }


}
