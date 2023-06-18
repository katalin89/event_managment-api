package ro.mycode.event_managmentapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AddEventRequest {
    @NotEmpty
    private String eventTitle;
    @NonNull
    private String description;
    @NonNull
    private LocalDate date;
    @NonNull
    private String location;
    @NonNull
    private  Long userId;
}
