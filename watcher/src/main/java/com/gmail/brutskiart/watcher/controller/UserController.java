package com.gmail.brutskiart.watcher.controller;

import com.gmail.brutskiart.watcher.service.UserService;
import com.gmail.brutskiart.watcher.service.dto.UserNotifyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.gmail.brutskiart.watcher.controller.ControllerConstants.MESSAGE_KEY;

@RestController
@RequiredArgsConstructor
@RequestMapping(
        value = "/notify",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Object> getCrypto(
            @RequestBody UserNotifyDto userNotifyDto,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            Set<String> errorSet = result.getFieldErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toSet());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of(
                                    MESSAGE_KEY,
                                    errorSet.toString()
                            )
                    );
        } else {
            UserNotifyDto savedUserNotifyDto = userService.post(userNotifyDto);
            return ResponseEntity.ok(savedUserNotifyDto);
        }
    }
}
