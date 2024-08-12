package telran.org.scotlandyard.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import telran.org.scotlandyard.model.Role;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_out")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User_out {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String phone;

    // HASH kode
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

//    @OneToOne(mappedBy = "user_out")
//    @JsonManagedReference
//    private Cart cart;

//    @OneToMany(mappedBy = "user_out",cascade = CascadeType.ALL)
//    @JsonManagedReference
//    private Set<Order> order = new HashSet<>();

//    @ManyToOne(cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "favorite_id", referencedColumnName = "id")
//    @JsonBackReference
//    @ToString.Exclude
//    private Favorite favorite;

    @OneToMany(mappedBy = "user_out",cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Favorite> favorites = new HashSet<>();
}
