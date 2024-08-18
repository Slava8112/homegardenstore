package telran.org.scotlandyard.security.modele;

import lombok.Data;

@Data
public class SignInRequest {

    private String login;
    private String password;
}
