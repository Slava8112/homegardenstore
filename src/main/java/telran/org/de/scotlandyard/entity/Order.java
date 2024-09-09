package telran.org.de.scotlandyard.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import telran.org.de.scotlandyard.model.Status;

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
    private double totalPrice;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<OrderItem> orderItems = new HashSet<>();

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "userEntityId", referencedColumnName = "id")
    @JsonBackReference
    @ToString.Exclude
    private UserEntity userEntity;

    @PreUpdate
    protected void onUpdate() {
        this.updatedAT = new Date();
    }
    public void addOrderItem(OrderItem item) {
        this.orderItems.add(item);
        recalculateTotalPrice(); // Пересчитываем общую стоимость после добавления элемента
    }
    // Метод для пересчета общей стоимости заказа
    private void recalculateTotalPrice() {
        totalPrice = orderItems.stream()
                .mapToDouble(item -> item.getPricePurshause() * item.getQuantity())
                .sum();
    }
}
