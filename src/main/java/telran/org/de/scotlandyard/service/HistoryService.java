package telran.org.de.scotlandyard.service;

import telran.org.de.scotlandyard.dto.orderdto.OrderDTO;
import java.util.List;

public interface HistoryService {
    List<OrderDTO> getUserOrderHistory(Long userId);
}
