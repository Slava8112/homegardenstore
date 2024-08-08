package telran.org.scotlandyard.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import telran.org.scotlandyard.model.Status;

import java.util.Date;

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

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_out_id", referencedColumnName = "id")
    @JsonBackReference
    @ToString.Exclude
    private User_out user_out;
}
