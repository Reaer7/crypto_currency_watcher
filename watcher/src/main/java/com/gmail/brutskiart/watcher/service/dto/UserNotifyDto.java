package com.gmail.brutskiart.watcher.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserNotifyDto {

    @JsonIgnore
    private Long id;

    @Size(max = 255, message = "Wrong username length! It's must be 255 character max")
    private String username;

    @Size(max = 20, message = "Wrong symbol length! It's must be 20 character max")
    private String symbol;
}
