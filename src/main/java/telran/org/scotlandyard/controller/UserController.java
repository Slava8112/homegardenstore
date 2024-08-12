package telran.org.scotlandyard.controller;

import telran.org.scotlandyard.dto.UserCreateDto;
import telran.org.scotlandyard.model.UserDto;
import telran.org.scotlandyard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserDto create(@RequestBody UserCreateDto userCreateDto) {
        return userService.create(userCreateDto);
    }

    @GetMapping
    public List<UserDto> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @GetMapping("/search")
    public UserDto findByEmail(@RequestParam String email) {
        return userService.findByEmail(email);
    }
}