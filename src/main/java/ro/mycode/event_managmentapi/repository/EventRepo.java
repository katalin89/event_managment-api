package ro.mycode.event_managmentapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.mycode.event_managmentapi.model.Event;

import javax.persistence.Id;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepo extends JpaRepository<Event,Long> {
    @Query("select distinct e.eventTitle from Event e")
    List<String>getAllEventTitle();

    @Transactional
    @Modifying
    @Query("select e from Event e where e.eventTitle=?1")
    List<Event>findEventWithTitle(String bookTitle);

    @Query("select e from Event e where e.eventTitle=?1")
    String getEvent(String eventTitle);

    @Query("select  e from Event  e where e.user.id=?1")
    List<Event>getAllUsersEvent(Long id);

    Optional<Event> findEventByEventTitle(String eventTitle);

    Event findEventById(Long id);
}
