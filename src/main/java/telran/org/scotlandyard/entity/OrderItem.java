package telran.org.scotlandyard.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orderitems")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderItem {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    private double pricePurshause;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "orderId", referencedColumnName = "id")
    @JsonBackReference
    @ToString.Exclude
    private Order order;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "productId", referencedColumnName = "id")
    @JsonBackReference
    @ToString.Exclude
    private Product product;
}
