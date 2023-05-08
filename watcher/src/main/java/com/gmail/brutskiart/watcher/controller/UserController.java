package com.gmail.brutskiart.watcher.controller;

import com.gmail.brutskiart.watcher.service.UserService;
import com.gmail.brutskiart.watcher.service.dto.UserNotifyDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "2. Управление оповещениями пользователей об изменении цены")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Регистрация оповещения пользователя об изменении цены")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ok", content = @Content(examples = {
                    @ExampleObject(
                            name = "Регистрация оповещения пользователя об изменении цены",
                            value = SwaggerConstants.CREATE_NOTIFY
                    )
            })),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
    })
    @PostMapping
    public ResponseEntity<Object> addNotify(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(examples = {
                    @ExampleObject(
                            name = "Регистрация оповещения пользователя об изменении цены",
                            value = SwaggerConstants.CREATE_NOTIFY
                    )
            }))
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
            UserNotifyDto savedUserNotifyDto = userService.addNotify(userNotifyDto);
            return ResponseEntity.status(HttpStatus.CREATED.value())
                    .body(savedUserNotifyDto);
        }
    }
}
