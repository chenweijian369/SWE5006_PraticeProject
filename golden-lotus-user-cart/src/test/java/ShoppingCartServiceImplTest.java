import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.nus.mapper.ChefMapper;
import com.nus.mapper.DishMapper;
import com.nus.mapper.ShoppingCartMapper;
import com.nus.pojo.dto.ShoppingCartDTO;
import com.nus.pojo.entity.Chef;
import com.nus.pojo.entity.Dish;
import com.nus.pojo.entity.ShoppingCart;
import com.nus.service.impl.ShoppingCartServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ShoppingCartServiceImplTest {

    @InjectMocks
    private ShoppingCartServiceImpl shoppingCartService;

    @Mock
    private ShoppingCartMapper shoppingCartMapper;

    @Mock
    private DishMapper dishMapper;

    @Mock
    private ChefMapper chefMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddToShoppingCart() {
        // Prepare test data
        ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
        shoppingCartDTO.setDishId(1L);
        shoppingCartDTO.setChefId(2L);

        Dish dish = new Dish();
        dish.setPrice(10.0);

        when(shoppingCartMapper.findDishInCart(shoppingCartDTO, 123L)).thenReturn(null);
        when(dishMapper.getById(1L)).thenReturn(dish);
        when(chefMapper.getById(2L)).thenReturn(new Chef());

        // Test the addToShoppingCart method
        shoppingCartService.addToShoppingCart(shoppingCartDTO);

        // Verify the shopping cart was updated correctly
        verify(shoppingCartMapper, times(1)).insert(any(ShoppingCart.class));
    }

    @Test
    public void testRemoveFromShoppingCart() {
        // Prepare test data
        ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
        shoppingCartDTO.setDishId(1L);
        shoppingCartDTO.setChefId(2L);

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setNumber(2);

        when(shoppingCartMapper.findDishInCart(shoppingCartDTO, 123L)).thenReturn(shoppingCart);

        // Test the removeFromShoppingCart method
        shoppingCartService.removeFromShoppingCart(shoppingCartDTO);

        // Verify the shopping cart was updated correctly
        verify(shoppingCartMapper, times(1)).updateById(anyLong(), anyInt(), anyDouble());
    }

    @Test
    public void testShowAll() {
        // Prepare test data
        List<ShoppingCart> shoppingCartList = new ArrayList<>();
        shoppingCartList.add(new ShoppingCart());
        shoppingCartList.add(new ShoppingCart());

        when(shoppingCartMapper.getByUserId(123L)).thenReturn(shoppingCartList);

        // Test the showAll method
        List<ShoppingCart> result = shoppingCartService.showAll();

        // Verify that the result matches the test data
        assertEquals(shoppingCartList.size(), result.size());
    }

    @Test
    public void testEmptyShoppingCart() {
        // Test the emptyShoppingCart method
        shoppingCartService.emptyShoppingCart();

        // Verify that the shopping cart was cleared
        verify(shoppingCartMapper, times(1)).deleteByUserId(123L);
    }

    @Test
    public void testCalculateTotal() {
        // Prepare test data
        List<Double> amounts = new ArrayList<>();
        amounts.add(10.0);
        amounts.add(20.0);

        when(shoppingCartMapper.getAmountByUserId(123L)).thenReturn(amounts);

        // Test the calculateTotal method
        Double total = shoppingCartService.calculateTotal();

        // Verify that the total amount is calculated correctly
        assertEquals(30.0, total, 0.001);
    }
}
