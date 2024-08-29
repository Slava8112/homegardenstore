package telran.org.scotlandyard.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import telran.org.scotlandyard.dto.userdto.UserCreateDto;
import telran.org.scotlandyard.dto.userdto.UserDto;
import telran.org.scotlandyard.entity.UserEntity;
import telran.org.scotlandyard.model.Role;
import telran.org.scotlandyard.security.AuthenticationService;
import telran.org.scotlandyard.security.modele.JwtAuthenticationResponse;
import telran.org.scotlandyard.security.modele.SignInRequest;
import telran.org.scotlandyard.service.UserService;
import telran.org.scotlandyard.service.converter.Converter;

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
    private final Converter<UserEntity, UserDto, UserCreateDto> converter;

    @Operation(summary = "Получение списка всех пользователей")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список пользователей успешно получен"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен")
    })
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<UserDto> getAll() {
        return userService.getAll().stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Получение пользователя по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно найден"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    @GetMapping("/{id}")
    public UserEntity getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @Operation(summary = "Регистрация нового пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Пользователь успешно зарегистрирован"),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос")
    })
    @PostMapping("/register")
    public ResponseEntity<UserDto> create(@RequestBody UserCreateDto userDto) {
    @PostMapping("/register") // Добавляем '/register' для регистрации
    public ResponseEntity<UserDto> create(@RequestBody UserCreateDto userDto, @RequestParam Role role) {
        UserEntity userEntity = converter.toEntity(userDto);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        UserDto createdUser = converter.toDto(userService.create(userEntity, role));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @Operation(summary = "Поиск пользователя по email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно найден"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    @GetMapping("/search")
    public UserEntity findByEmail(@RequestParam String email) {
        return userService.findByEmail(email);
    }

    @Operation(summary = "Удаление пользователя по email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно удален"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    @DeleteMapping

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void delete(@RequestParam String email) {
        userService.deleteByEmail(email);
    }

    @Operation(summary = "Аутентификация пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Аутентификация успешна, JWT токен возвращен"),
            @ApiResponse(responseCode = "401", description = "Неверный логин или пароль")
    })
    @PostMapping("/login")
    public JwtAuthenticationResponse login(@RequestBody SignInRequest request) {
        return authenticationService.authenticate(request);
    }
}