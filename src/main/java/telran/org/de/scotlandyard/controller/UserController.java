package telran.org.de.scotlandyard.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import telran.org.de.scotlandyard.converter.Converter;
import telran.org.de.scotlandyard.dto.userdto.UserCreateDto;
import telran.org.de.scotlandyard.dto.userdto.UserDto;
import telran.org.de.scotlandyard.entity.UserEntity;
import telran.org.de.scotlandyard.security.AuthenticationService;
import telran.org.de.scotlandyard.security.model.JwtAuthenticationResponse;
import telran.org.de.scotlandyard.security.model.SignInRequest;
import telran.org.de.scotlandyard.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;
    private final Converter<UserEntity, UserDto, UserCreateDto> userConverter;

//    @Operation(summary = "Получение списка всех пользователей")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Список пользователей успешно получен"),
//            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
//            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
//    })
//    @GetMapping
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<UserDto>> getAll() {
        log.debug("Получение всех пользователей");
        List<UserDto> users = userService.getAll().stream()
                .map(userConverter::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

//    @Operation(summary = "Получение пользователя по ID")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Пользователь успешно найден"),
//            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
//    })
//    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable Long id) {
        UserEntity userEntity = userService.getById(id);
        return ResponseEntity.ok(userConverter.toDto(userEntity));
    }

    @Operation(summary = "Регистрация нового пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Пользователь успешно зарегистрирован"),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос")
    })
    @PostMapping("/register")
    public ResponseEntity<UserDto> create(@RequestBody UserCreateDto userDto) {
        UserEntity userEntity = userConverter.toEntity(userDto);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        UserDto createdUser = userConverter.toDto(userService.create(userEntity));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @Operation(summary = "Редактирование профиля пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Пользователь успешно изменен"),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    @PutMapping("/{email}")
    public ResponseEntity<UserDto> edit(@PathVariable String email, @Valid @RequestBody UserCreateDto userCreateDto) {
        log.debug("Attempting to edit user with ID: {}", email);
        UserEntity updatedUser = userService.edit(email, userCreateDto);
        return ResponseEntity.ok(userConverter.toDto(updatedUser));
    }

//    @Operation(summary = "Поиск пользователя по email")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Пользователь успешно найден"),
//            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
//    })
//    @GetMapping("/search")
    public ResponseEntity<UserDto> findByEmail(@RequestParam String email) {
        UserEntity userEntity = userService.findByEmail(email);
        return ResponseEntity.ok(userConverter.toDto(userEntity));
    }

    @Operation(summary = "Удаление пользователя по email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно удален"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    @DeleteMapping
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> delete(@RequestParam String email) {
        userService.deleteByEmail(email);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Аутентификация пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешная аутентификация",
                    content = @Content(schema = @Schema(implementation = JwtAuthenticationResponse.class))),
            @ApiResponse(responseCode = "401", description = "Неверный логин или пароль")
    })
    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody SignInRequest request) {
        JwtAuthenticationResponse response = authenticationService.authenticate(request);
        return ResponseEntity.ok(response);
    }
}