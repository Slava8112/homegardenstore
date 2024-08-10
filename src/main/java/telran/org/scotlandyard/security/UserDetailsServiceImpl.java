package telran.org.scotlandyard.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import telran.org.scotlandyard.entity.User_out;
import telran.org.scotlandyard.service.UserService;

import java.util.List;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User_out userEntity = userService.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with login " + username + " not found"));

        return new org.springframework.security.core.userdetails.User(userEntity.getEmail(),
                userEntity.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
