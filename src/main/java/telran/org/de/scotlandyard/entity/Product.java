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

    private double price;

    private String image;

    @Column(name = "discountPrice", nullable = true)
    private Double discountPrice = 0.0;

    private Date createdAt = new Date();

    private Date updatedAt = new Date();

    @ManyToOne
    @JoinColumn(name = "category_id")
//    @JsonBackReference
    @ToString.Exclude
    private Category category;

    public Product(String name, String description, double price, Category category, String image) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.image = image;
    }
//    @Column(name = "category_id", nullable = false)
//    @Column(insertable=false, updatable=false)
//    private Long categoryId;
//    @ManyToOne(cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "category_id", referencedColumnName = "id")
//    @JsonBackReference
}
