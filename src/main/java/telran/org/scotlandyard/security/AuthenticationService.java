package telran.org.scotlandyard.security;

import telran.org.scotlandyard.security.modele.JwtAuthenticationResponse;
import telran.org.scotlandyard.security.modele.SignInRequest;

public interface AuthenticationService {
    JwtAuthenticationResponse authenticate(SignInRequest request);
}
