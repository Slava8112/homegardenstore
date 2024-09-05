package telran.org.de.scotlandyard.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import telran.org.de.scotlandyard.entity.Order;
import telran.org.de.scotlandyard.entity.UserEntity;
import telran.org.de.scotlandyard.repository.OrderRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllOrders() {
        List<Order> mockOrders = List.of(new Order(), new Order());
        when(orderRepository.findAll()).thenReturn(mockOrders);

        List<Order> orders = orderService.getAllOrders();

        assertNotNull(orders);
        assertEquals(2, orders.size());
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    void testFindById_Success() {
        Order mockOrder = new Order();
        when(orderRepository.findById(1L)).thenReturn(Optional.of(mockOrder));

        Order order = orderService.findById(1L);

        assertNotNull(order);
        verify(orderRepository, times(1)).findById(1L);
    }

    @Test
    void testFindById_NotFound() {
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> orderService.findById(1L));
        verify(orderRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateOrder() {
        Order mockOrder = new Order();
        when(orderRepository.save(mockOrder)).thenReturn(mockOrder);

        Order order = orderService.create(mockOrder);

        assertNotNull(order);
        verify(orderRepository, times(1)).save(mockOrder);
    }

    @Test
    void testGetAllByCurrentUser() {
        SecurityContext securityContext = mock(SecurityContext.class);
        Authentication authentication = mock(Authentication.class);

        when(authentication.getName()).thenReturn("testuser@example.com");
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        UserEntity mockUser = new UserEntity();
        when(userService.findByEmail("testuser@example.com")).thenReturn(mockUser);
        List<Order> mockOrders = List.of(new Order(), new Order());
        when(orderRepository.findAllByUserEntity(mockUser)).thenReturn(mockOrders);

        List<Order> orders = orderService.getAllByCurrentUser();

        assertNotNull(orders);
        assertEquals(1, orders.size());
        verify(orderRepository, times(1)).findAllByUserEntity(mockUser);
    }
}
