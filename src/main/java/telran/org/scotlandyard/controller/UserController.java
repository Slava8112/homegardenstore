package telran.org.scotlandyard.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import telran.org.scotlandyard.dto.user.UserCreateDto;
import telran.org.scotlandyard.dto.user.UserDto;
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

    @GetMapping
   // @PreAuthorize("hasAuthority('ROLE_ADMIN')")

    public List<UserDto> getAll() {
        return userService.getAll().stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")

    public UserEntity getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PostMapping("/register") // Добавляем '/register' для регистрации
    public ResponseEntity<UserDto> create(@RequestBody UserCreateDto userDto, @RequestParam Role role) {
        UserEntity userEntity = converter.toEntity(userDto);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        UserDto createdUser = converter.toDto(userService.create(userEntity, role));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping("/search")
    public UserEntity findByEmail(@RequestParam String email) {
        return userService.findByEmail(email);
    }

    @DeleteMapping
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void delete(@RequestParam String email) {
        userService.deleteByEmail(email);
    }

    @PostMapping("/login")
    public JwtAuthenticationResponse login(@RequestBody SignInRequest request) {
        return authenticationService.authenticate(request);
    }
}