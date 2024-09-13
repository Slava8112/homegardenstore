package telran.org.de.scotlandyard.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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


    @OneToOne
    @JoinColumn(name = "user_entity_id", referencedColumnName = "id")
    private UserEntity userEntity;

//    @OneToOne()
//    @JoinColumn(name = "userEntity", referencedColumnName = "id")
//    private UserEntity userEntity;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "cart_id")
//    private Set<CartItems> cartItems = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
    @JsonManagedReference
    private Set<CartItems> cartItems = new HashSet<>();


}
