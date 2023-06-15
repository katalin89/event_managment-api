package ro.mycode.event_managmentapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.mycode.event_managmentapi.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    @Query("select distinct  u.firstName,u.lastName from User u")
    List<String>getAllUsersName();

    @Query("select u from User u where u.email=?1  and u.password=?2")
    Optional<User>login(String user,String password);
}

