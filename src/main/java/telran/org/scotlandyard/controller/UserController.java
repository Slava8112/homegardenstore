package telran.org.scotlandyard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import telran.org.scotlandyard.entity.UserEntity;
//import telran.org.scotlandyard.security.AuthenticationService;
//import telran.org.scotlandyard.security.modele.JwtAuthenticationResponse;
//import telran.org.scotlandyard.security.modele.SignInRequest;
import telran.org.scotlandyard.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/userEntity")
@RequiredArgsConstructor
public class UserController {


    public final UserService userService;

//    @Autowired
//    private AuthenticationService authenticationService;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;

//    @Autowired
//    private Converter<UserEntity, UserDto, UserCreateDto> converter;

//    @GetMapping
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
//    public List<UserEntity> getAll() {
//     public List<UserDto> getAll() {
//        return userService.getAll();
//                .stream()
//                .map(user -> converter.toDto(user))
//                .collect(Collectors.toList());
//    }

    @GetMapping
    public List<UserEntity> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserEntity getById(@PathVariable Long id) {

        return userService.getById(id);
    }

    @PostMapping
    public UserEntity create(@RequestBody UserEntity userEntity) {

        return userService.create(userEntity);
    }

    @GetMapping("/search")
    public UserEntity findByEmail(@RequestParam String email) {
        return userService.findByEmail(email);
    }

    @DeleteMapping
    public void delete(@RequestParam String email) {
        userService.deleteByEmail(email);
    }

//    @PostMapping("/login")
//    public JwtAuthenticationResponse login(@RequestBody SignInRequest request) {
//        return authenticationService.authenticate(request);
//    }
}