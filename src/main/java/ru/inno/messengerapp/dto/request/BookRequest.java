package ru.inno.messengerapp.dto.request;

import lombok.Value;
import org.springframework.lang.NonNull;

@Value
public class BookRequest {
    @NonNull
    String name;
    @NonNull
    String author;
    Long  clientId;
}
