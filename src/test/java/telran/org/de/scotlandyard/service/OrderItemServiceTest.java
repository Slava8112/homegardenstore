package telran.org.de.scotlandyard.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import telran.org.de.scotlandyard.entity.OrderItem;
import telran.org.de.scotlandyard.entity.Product;
import telran.org.de.scotlandyard.repository.OrderItemsRepository;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderItemServiceTest {

    @Mock
    private OrderItemsRepository orderItemsRepository;

    @Mock
    private ProductService productService;

    @InjectMocks
    private OrderItemServiceImpl orderItemService;

    private OrderItem mockOrderItem;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockOrderItem = new OrderItem();
        mockOrderItem.setId(1L);
        mockOrderItem.setQuantity(2);
    }

    @Test
    void testGetAllOrderItems() {
        List<OrderItem> mockOrderItems = List.of(new OrderItem(), new OrderItem());
        when(orderItemsRepository.findAll()).thenReturn(mockOrderItems);

        List<OrderItem> orderItems = orderItemService.getAllOrderItem();

        assertNotNull(orderItems);
        assertEquals(2, orderItems.size());
        verify(orderItemsRepository, times(1)).findAll();
    }

    @Test
    void testGetById_Success() {
        when(orderItemsRepository.findById(1L)).thenReturn(Optional.of(mockOrderItem));
        OrderItem orderItem = orderItemService.getById(1L);
        System.out.println("OrderItem: " + orderItem);

        assertNotNull(orderItem);
        assertEquals(1L, orderItem.getId());
        verify(orderItemsRepository, times(1)).findById(1L);
    }

    @Test
    void testAddOrderItem() {
        Product mockProduct = new Product();
        OrderItem mockOrderItem = new OrderItem();
        when(productService.getById(1L)).thenReturn(mockProduct);
        when(orderItemsRepository.save(mockOrderItem)).thenReturn(mockOrderItem);

        OrderItem orderItem = orderItemService.addOrderItem(1L, mockOrderItem);

        assertNotNull(orderItem);
        assertEquals(mockProduct, orderItem.getProduct());
        verify(orderItemsRepository, times(1)).save(mockOrderItem);
    }

    @Test
    void testDeleteById() {
        doNothing().when(orderItemsRepository).deleteById(1L);

        orderItemService.deleteById(1L);

        verify(orderItemsRepository, times(1)).deleteById(1L);
    }
}

