package telran.org.de.scotlandyard.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
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

//    @Column(insertable=false, updatable=false)
//    private Long categoryId;

    private double price;

    private String image;

    @Column(name = "discountPrice", nullable = true)
    private Double discountPrice = 0.0;

    private Date createdAt = new Date();

    private Date updatedAt = new Date();

//    @ManyToOne(cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "category_id", referencedColumnName = "id")
//    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "category_id")
    @ToString.Exclude
    private Category category;

    public Product(String name, String description, double price, String image) {
    this.name=name;
    this.description=description;
    //this.categoryId=categoryId;
    this.price=price;
    this.image=image;
    }
}
