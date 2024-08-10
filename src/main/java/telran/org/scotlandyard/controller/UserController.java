package telran.org.scotlandyard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import telran.org.scotlandyard.entity.User_out;
import telran.org.scotlandyard.security.AuthenticationService;
import telran.org.scotlandyard.security.modele.JwtAuthenticationResponse;
import telran.org.scotlandyard.security.modele.SignInRequest;
import telran.org.scotlandyard.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user_out")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    public UserService userService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Autowired
//    private Converter<User_out, UserDto, UserCreateDto> converter;

//    @GetMapping
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
//    public List<User_out> getAll() {
//     public List<UserDto> getAll() {
//        return userService.getAll();
//                .stream()
//                .map(user -> converter.toDto(user))
//                .collect(Collectors.toList());
//    }

    @PostMapping
    public User_out create(@RequestBody User_out user_out) {

        return userService.create(user_out);
    }

    @GetMapping
    public List<User_out> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User_out getById(@PathVariable Long id) {

        return userService.getById(id);
    }

    @GetMapping("/search")
    public User_out findByEmail(@RequestParam String email) {
        return userService.findByEmail(email);
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