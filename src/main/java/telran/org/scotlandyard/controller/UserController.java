package telran.org.scotlandyard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import telran.org.scotlandyard.entity.User_out;
import telran.org.scotlandyard.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user_out")
@RequiredArgsConstructor
public class UserController {

    public final UserService userService;

   @PostMapping
   public User_out create(@RequestBody User_out user_out){

       return  userService.create(user_out);
   }
   @GetMapping
    public List<User_out> getAll(){
       return userService.getAll();
    }
   @GetMapping("/{id}")
    public User_out getById(@PathVariable Long id){

       return userService.getById(id);
   }
     @GetMapping("/search")
    public User_out findByEmail(@RequestParam String email){
       return userService.findByEmail(email);
   }
@DeleteMapping
    public void delete(@RequestParam String email){
       userService.deleteByEmail(email);
}

}
