package telran.org.scotlandyard.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "carts")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonBackReference
    private User_out user_out;

    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Cartitems> cartitems = new HashSet<>();
}
