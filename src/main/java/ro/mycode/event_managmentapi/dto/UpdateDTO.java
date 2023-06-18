package ro.mycode.event_managmentapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@SuperBuilder
public class UpdateDTO {
    @NonNull
    private Long id;
    private String eventTitle;
    private String description;
    private LocalDate date;
    private String location;
    @NonNull
    private Long userId;
}
