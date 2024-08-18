package telran.org.scotlandyard.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

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


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @JsonBackReference
    @ToString.Exclude
    private Product product;

}
