package telran.org.scotlandyard.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "products")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    private String description;

    private double price;

    private String image;

    private double discountprice;

    private Date createdAt = new Date();
//Verify  createdAt  on null
    private Date updatedAt = new Date();

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @JsonBackReference
    @ToString.Exclude
    private Category category;

}
