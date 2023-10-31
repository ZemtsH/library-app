package ru.inno.messengerapp.dto.request;

import lombok.Value;
import org.springframework.lang.NonNull;

@Value
public class ClientRequest {
    @NonNull
    String firstName;
    @NonNull
    String lastName;
    @NonNull
    String email;
}
