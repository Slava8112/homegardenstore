package telran.org.scotlandyard.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

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

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @JsonBackReference
    @ToString.Exclude
    private Product product;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "userEntityId", referencedColumnName = "id")
    @JsonBackReference
    @ToString.Exclude
    private UserEntity userEntity;
}
