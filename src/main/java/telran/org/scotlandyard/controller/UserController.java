package telran.org.scotlandyard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import telran.org.scotlandyard.dto.UserCreateDto;
import telran.org.scotlandyard.dto.UserDto;
import telran.org.scotlandyard.entity.UserEntity;
import telran.org.scotlandyard.security.AuthenticationService;
import telran.org.scotlandyard.security.modele.JwtAuthenticationResponse;
import telran.org.scotlandyard.security.modele.SignInRequest;
import telran.org.scotlandyard.service.UserService;
import telran.org.scotlandyard.service.converter.Converter;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/users") // Измененный путь
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;
    private final Converter<UserEntity, UserDto, UserCreateDto> converter;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
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
    public ResponseEntity<UserDto> create(@RequestBody UserCreateDto userDto) {
        UserEntity userEntity = converter.toEntity(userDto);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        UserDto createdUser = converter.toDto(userService.create(userEntity));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping("/search")
    public UserEntity findByEmail(@RequestParam String email) {
        return userService.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " not found"));
    }

    @DeleteMapping
    public void delete(@RequestParam String email) {
        userService.deleteByEmail(email);
    }

    @PostMapping("/login")
    public JwtAuthenticationResponse login(@RequestBody SignInRequest request) {
        return authenticationService.authenticate(request);
    }
}
//package telran.org.scotlandyard.controller;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//import telran.org.scotlandyard.dto.UserCreateDto;
//import telran.org.scotlandyard.dto.UserDto;
//import telran.org.scotlandyard.entity.UserEntity;
//import telran.org.scotlandyard.security.AuthenticationService;
//import telran.org.scotlandyard.security.modele.JwtAuthenticationResponse;
//import telran.org.scotlandyard.security.modele.SignInRequest;
//import telran.org.scotlandyard.service.UserService;
//import telran.org.scotlandyard.service.converter.Converter;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/userEntity")
//@RequiredArgsConstructor
//public class UserController {
//
//    private final UserService userService;
//    private final AuthenticationService authenticationService;
//    private final PasswordEncoder passwordEncoder;
//    private final Converter<UserEntity, UserDto, UserCreateDto> converter;
//
//    @GetMapping
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    public List<UserDto> getAll() {
//        return userService.getAll().stream()
//                .map(converter::toDto)
//                .collect(Collectors.toList());
//    }
//
//    @GetMapping("/{id}")
//    public UserEntity getById(@PathVariable Long id) {
//        return userService.getById(id);
//    }
//
////    @PostMapping
////    public UserDto create(@RequestBody UserCreateDto userDto) {
////        UserEntity userEntity = converter.toEntity(userDto);
////        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
////        return converter.toDto(userService.create(userEntity));
////    }
//
//    @PostMapping
//    public ResponseEntity<UserDto> create(@RequestBody UserCreateDto userDto) {
//        UserEntity userEntity = converter.toEntity(userDto);
//        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
//        UserDto createdUser = converter.toDto(userService.create(userEntity));
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
//    }
//    @GetMapping("/search")
//    public UserEntity findByEmail(@RequestParam String email) {
//        return userService.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " not found"));
//    }
//
//    @DeleteMapping
//    public void delete(@RequestParam String email) {
//        userService.deleteByEmail(email);
//    }
//
//    @PostMapping("/login")
//    public JwtAuthenticationResponse login(@RequestBody SignInRequest request) {
//        return authenticationService.authenticate(request);
//    }
//}