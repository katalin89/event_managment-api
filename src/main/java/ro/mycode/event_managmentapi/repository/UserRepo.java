package ro.mycode.event_managmentapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.mycode.event_managmentapi.model.Event;
import ro.mycode.event_managmentapi.model.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    @Query("select distinct  u.firstName,u.lastName from User u")
    List<String>getAllUsersName();

    @Query("select e from Event  e where e.user.id=?1 and e.eventTitle=?2")
    List<Event>userHaveEvent(Long id,String eventTitle);

    @Transactional
    @Modifying
    @Query("delete  from User u where u.lastName=?1 and u.lastName=?2")
    void deleteByName(String firstName,String latName);

    @Transactional
    @Modifying
    @Query("delete  from User u where u.id=?1")
    void  deleteById(Long id);


    @Query("select u from User u where u.email=?1  and u.password=?2")
    Optional<User>login(String user,String password);

    @Query("select  u from User  u where u.firstName=?1 and u.lastName=?2 and u.email=?3 and u.password=?4")
    Optional<User>signUp(String firstName,String lastName,String email,String password);

    @Query("select  u from Event u where u.user.id=?1")
    List<Event>getAllUsersEvent(Long id );


}

