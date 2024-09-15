package telran.org.de.scotlandyard.security;

import telran.org.de.scotlandyard.security.model.JwtAuthenticationResponse;
import telran.org.de.scotlandyard.security.model.SignInRequest;

public interface AuthenticationService {

    JwtAuthenticationResponse authenticate(SignInRequest request);
}
