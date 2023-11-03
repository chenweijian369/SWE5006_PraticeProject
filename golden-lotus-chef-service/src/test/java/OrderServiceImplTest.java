import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.nus.mapper.OrderDetailMapper;
import com.nus.mapper.OrderMapper;
import com.nus.pojo.entity.Order;
import com.nus.pojo.entity.OrderDetail;
import com.nus.pojo.vo.OrderVO;
import com.nus.service.impl.OrderServiceImpl;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderMapper orderMapper;

    @Mock
    private OrderDetailMapper orderDetailMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetOrderDetailsByChefId() {
        Long chefId = 1L;
        Order order1 = new Order();
        order1.setUserId(1L);

        Order order2 = new Order();
        order2.setUserId(2L);
        List<Order> orderList = new ArrayList<>();

        when(orderMapper.getByChefId(chefId)).thenReturn(orderList);

        OrderDetail orderDetail1 = new OrderDetail();
        OrderDetail orderDetail2 = new OrderDetail();
        List<OrderDetail> orderDetails = new ArrayList<>();

        when(orderDetailMapper.getByOrderIdAndChefId(1L, chefId)).thenReturn(orderDetails);
        when(orderDetailMapper.getByOrderIdAndChefId(2L, chefId)).thenReturn(orderDetails);

        List<OrderVO> orderVOList = orderService.getOrderDetailsByChefId(chefId);

        assertEquals(0, orderVOList.size());
    }

    @Test
    public void testCompleteOrder() {
        Long orderId = 1L;

        doNothing().when(orderMapper).completeOrderById(orderId);

        orderService.completeOrder(orderId);

        verify(orderMapper).completeOrderById(orderId);
    }
}
