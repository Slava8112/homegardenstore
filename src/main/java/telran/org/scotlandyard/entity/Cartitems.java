package telran.org.scotlandyard.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cartitems")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Cartitems {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cartitem_id")
    private Set<Cart> carts = new HashSet<>();

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cartitem_id")
    private Product product;

}
