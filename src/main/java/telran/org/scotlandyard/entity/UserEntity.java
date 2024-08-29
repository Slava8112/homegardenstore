package telran.org.scotlandyard.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.*;
import telran.org.scotlandyard.model.Role;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "userEntity")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserEntity {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String phone;

    private String password;

   @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_CLIENT;

    @OneToMany(mappedBy = "userEntity",cascade = CascadeType.ALL)

    @JsonManagedReference
    private Set<Favorite> favorites = new HashSet<>();

    public UserEntity(String name, String email, String phone, String password) {
    }
}