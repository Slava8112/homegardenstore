package telran.org.scotlandyard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import telran.org.scotlandyard.entity.User_out;
import telran.org.scotlandyard.service.UserService;

@RestController
@RequestMapping("User_out")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

   @PostMapping
   public User_out create(@RequestBody User_out user_out){
       return  userService.create(user_out);
   }
   @GetMapping("/{id}")
    public User_out getById(@PathVariable Long id){
       return userService.getById(id);
   }

}
