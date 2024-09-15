package telran.org.de.scotlandyard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import telran.org.de.scotlandyard.converter.OrderConverter;
import telran.org.de.scotlandyard.dto.orderdto.OrderDTO;
import telran.org.de.scotlandyard.entity.Order;
import telran.org.de.scotlandyard.entity.UserEntity;
import telran.org.de.scotlandyard.repository.OrderRepository;
import telran.org.de.scotlandyard.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderConverter orderConverter;

    @Override
    public List<OrderDTO> getUserOrderHistory(Long userId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Order> orders = orderRepository.findAllByUserEntity(userEntity);
        return orders.stream()
                .map(orderConverter::toDto)
                .collect(Collectors.toList());
    }
}
