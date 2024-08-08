package telran.org.scotlandyard.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "favorites")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Favorite {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "favorite_id")
    private Set<Product> products = new HashSet<>();

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_out_id", referencedColumnName = "id")
    @JsonBackReference
    @ToString.Exclude
    private User_out user_out;
}
