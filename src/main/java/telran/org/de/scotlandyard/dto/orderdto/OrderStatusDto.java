package telran.org.de.scotlandyard.dto.orderdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import telran.org.de.scotlandyard.model.Status;

@Getter
@Setter
@AllArgsConstructor
public class OrderStatusDto {

    private long id;

    private Status status;
}
