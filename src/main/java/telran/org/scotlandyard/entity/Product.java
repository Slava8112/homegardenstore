package telran.org.scotlandyard.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

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

//    @ManyToOne(cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "Product_id", referencedColumnName = "id")
//    @JsonBackReference
//    @ToString.Exclude
//    private Product product;

}
