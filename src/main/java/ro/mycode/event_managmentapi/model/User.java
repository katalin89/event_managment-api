package ro.mycode.event_managmentapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="User")
@Table(name="users")
@SuperBuilder
public class User implements Comparable<User> {
    @Id
    @SequenceGenerator(name = "user_sequense", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")

    private Long id;

    @Column(name = "first_name", nullable = false)
    @Size(min = 2, message = "Name must have min two caracters")
    private String firstName;

    @Column(name = "lastName", nullable = false)
    @NotBlank(message = "Field must not be the empty string")
    private String lastName;

    @Column(name = "email", nullable = false)
    @Email(message = "That string field must be a valid email address.")
    private String email;

    @Column(name = "age", nullable = false)
    @Min(value = 18, message = "A student must be min 18 years")
    private int age;

    @Column(name = "passsword", nullable = false)
    @Size(min = 4, message = "Passsword must have four characters")
    private String password;

    // trebuie sa urmeze o variabila dupa anotatie


    @Override
    public int compareTo(User o) {
        if (this.age > o.age) {
            return 1;
        }
        if (this.age < o.age) {
            return -1;
        } else
            return 0;
    }

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    @JsonBackReference
    @ToString.Exclude
    //variabila
   List<Event> events=new ArrayList<>();


    public void addEvent(Event event){
        this.events.add(event);
        event.setUser(this);
    }

    public  void deleteEvent(Event event)throws EventNotFoundException{
        if(exists(event.getId())){
            this.events.remove(event);
            event.setUser(null);
        }else
            throw new EventNotFoundException();
    }

    public boolean exists(Long id){
        for(Event e:events){
            if(e.getId()==id){
                return  true;
            }

        }
        return  false;
    }
}






