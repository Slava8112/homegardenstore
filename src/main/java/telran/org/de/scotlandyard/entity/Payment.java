package telran.org.de.scotlandyard.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Payment {

    private Long id;

    private Long orderId;

    private double amount;

}
