package ro.mycode.event_managmentapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.ToString;
import lombok.experimental.SuperBuilder;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="Event")
@Table(name="events")
//putem adauga events
@SuperBuilder


public class Event implements Comparable<Event>{

    @Id
    @SequenceGenerator(name="event_sequence",sequenceName = "event_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "event_sequence")
    private Long id;


    @Column(name = "event_title", nullable = false)
    @NotBlank(message="String field cannot be an empty string")
    private String eventTitle;

    @Column(name="description",nullable = false)
    private String description;

    @Column(name="date",nullable = false)
    private LocalDate date;

    @Column(name="location",nullable = false)
    private String location;

    @ManyToOne
    @JoinColumn(
            name="user_id",
            referencedColumnName  ="id",
            foreignKey=@ForeignKey(name="user_id_fk")
    )

    @JsonBackReference
    @ToString.Exclude
    private User user;

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public int compareTo(Event o) {
        if(this.date.compareTo(o.date)>0){
            return 1;
        }
        if(this.date.compareTo(o.date)<0){
            return -1;
        }else
        return 0;
    }


    @Override
    public boolean equals(Object o){
        Event event=(Event)o;
        return  this.eventTitle.equals(event.eventTitle);
    }
}
