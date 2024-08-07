package telran.org.scotlandyard.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import telran.org.scotlandyard.model.Status;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date createdAT = new Date();

    private String DeliveryAddress;
    private String ContactPhone;
    private String DeliveryMethod;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Date updatedAT = new Date();

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "orders_id")
    private Set<Product> products = new HashSet<>();
}
