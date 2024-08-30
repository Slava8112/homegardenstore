package telran.org.de.scotlandyard.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import telran.org.de.scotlandyard.security.model.JwtAuthenticationResponse;
import telran.org.de.scotlandyard.security.model.SignInRequest;


@Service
public class AuthenticationServiceImpl implements AuthenticationService{
    private static final Logger log = LoggerFactory.getLogger(AuthenticationServiceImpl.class);
    @Autowired(required = false)
    private  AuthenticationManager authenticationManager;
@Autowired
    private  UserDetailsService userDetailsService;
@Autowired
    private  JwtService jwtService;

    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
    }


    @Override
    public JwtAuthenticationResponse authenticate(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getLogin(),
                        request.getPassword()));
log.debug("Authentication secsessful {}", request.getLogin());
        UserDetails user = userDetailsService.loadUserByUsername(request.getLogin());
        String token = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(token);
    }
    }

