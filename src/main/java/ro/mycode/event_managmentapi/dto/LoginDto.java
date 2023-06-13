package ro.mycode.event_managmentapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@SuperBuilder
public class LoginDto {
    @NonNull
    private String user;
    @NonNull
    private String password;
}
