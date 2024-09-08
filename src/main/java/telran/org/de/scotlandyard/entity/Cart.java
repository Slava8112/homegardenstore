package telran.org.de.scotlandyard.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JoinColumn(name = "userEntity", referencedColumnName = "id")
    @JsonBackReference
    private UserEntity userEntity;

@OneToMany(cascade = CascadeType.ALL)
@JoinColumn(name = "cart_id")
private Set<CartItems> cartItems = new HashSet<>();



}
