import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.nus.exception.AddressBookBusinessException;
import com.nus.mapper.AddressMapper;
import com.nus.mapper.OrderDetailMapper;
import com.nus.mapper.OrderMapper;
import com.nus.mapper.ShoppingCartMapper;
import com.nus.pojo.dto.OrderSubmitDTO;
import com.nus.pojo.entity.Address;
import com.nus.pojo.entity.Order;
import com.nus.pojo.entity.OrderDetail;
import com.nus.pojo.entity.ShoppingCart;
import com.nus.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.transaction.annotation.Transactional;

public class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private ShoppingCartMapper shoppingCartMapper;

    @Mock
    private AddressMapper addressMapper;

    @Mock
    private OrderMapper orderMapper;

    @Mock
    private OrderDetailMapper orderDetailMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

//    @Test
//    public void testSubmitOrder() {
//        // Prepare test data
//        OrderSubmitDTO ordersSubmitDTO = new OrderSubmitDTO();
//        ordersSubmitDTO.setAddressId(3L);
//        // ... Set other properties
//
//        Address address = new Address();
//        address.setId(3L);
//        address.setDetailLocation("123 Main St");
//        // ... Set other address properties
//
//        List<ShoppingCart> shoppingCartList = new ArrayList<>();
//        // ... Add shopping cart items
//
//        Order order = new Order();
//        // ... Set order properties
//
//        List<OrderDetail> orderDetailList = new ArrayList<>();
//        // ... Add order detail items
//
//        // Mock the dependencies
//        when(addressMapper.getById(ordersSubmitDTO.getAddressId())).thenReturn(address);
//        when(shoppingCartMapper.getByUserId(anyLong())).thenReturn(shoppingCartList);
//
//        // Test the submitOrder method
////        assertDoesThrow(() -> orderService.submitOrder(ordersSubmitDTO));
////        assertThrows(() -> orderService.submitOrder(ordersSubmitDTO));
//        // Verify that the order was saved with the correct properties
//        verify(orderMapper, times(1)).insert(any(Order.class));
//        verify(orderDetailMapper, times(1)).insertBatch(anyList());
//        verify(shoppingCartMapper, times(1)).deleteByUserId(anyLong());
//    }

    @Test
    public void testSubmitOrderWithNullAddress() {
        // Prepare test data
        OrderSubmitDTO ordersSubmitDTO = new OrderSubmitDTO();
        ordersSubmitDTO.setAddressId(1L);
        // ... Set other properties

        // Mock the dependencies to return null for address
        when(addressMapper.getById(ordersSubmitDTO.getAddressId())).thenReturn(null);

        // Test the submitOrder method and expect an exception
        assertThrows(AddressBookBusinessException.class, () -> orderService.submitOrder(ordersSubmitDTO));

        // Verify that the order was not saved and shopping cart was not modified
        verify(orderMapper, never()).insert(any(Order.class));
        verify(orderDetailMapper, never()).insertBatch(anyList());
        verify(shoppingCartMapper, never()).deleteByUserId(anyLong());
    }
}
