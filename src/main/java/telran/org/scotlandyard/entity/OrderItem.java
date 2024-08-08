package telran.org.scotlandyard.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orderitems")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    private double pricePurshause;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    @JsonBackReference
    @ToString.Exclude
    private Order order;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "orderitems_id")
    private Set<Product> products = new HashSet<>();
}
