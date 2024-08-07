package telran.org.scotlandyard.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class Product {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    private String description;

    private double price;

    private String category;

    //private Date receipt_Data = new Date();

    private String image;

    private double discountprice;

    private Date createdAt = new Date();

    private Date updatedAt = new Date();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "products_id")
    private Set<Product> products =new HashSet<>();

}
