import com.nus.mapper.ChefCategoryMapper;
import com.nus.mapper.ChefMapper;
import com.nus.pojo.dto.ChefAccountDTO;
import com.nus.pojo.entity.Chef;
import com.nus.pojo.entity.ChefCategory;
import com.nus.service.impl.ChefServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.ArrayList;


public class ChefServiceImplTest {

    @InjectMocks
    private ChefServiceImpl chefService;

    @Mock
    private ChefMapper chefMapper;

    @Mock
    private ChefCategoryMapper chefCategoryMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetById() {
        Long chefId = 1L;
        Chef chef = new Chef();
        chef.setId(chefId);
        chef.setPassword("myPassword"); // Password should be masked
        when(chefMapper.getById(chefId)).thenReturn(chef);

        Chef result = chefService.getById(chefId);

        // Ensure password is masked
        assertEquals("****", result.getPassword());
    }

    @Test
    public void testClassifyChefCategoryById() {
        Long categoryId = 1L;
        when(chefCategoryMapper.getByChefIdAndCategoryId(1L, categoryId)).thenReturn(new ArrayList<>());

        assertDoesNotThrow(() -> chefService.classifyChefCategoryById(categoryId));

        verify(chefCategoryMapper).insert(Mockito.any(ChefCategory.class));
    }

    @Test
    public void testDeleteChefCategoryById() {
        Long categoryId = 1L;
        assertDoesNotThrow(() -> chefService.deleteChefCategoryById(categoryId));

        verify(chefCategoryMapper).deleteByCategoryId(categoryId);
    }

    @Test
    public void testUpdateAccount() {
        ChefAccountDTO chefAccountDTO = new ChefAccountDTO();
        chefAccountDTO.setId(1L);
        chefAccountDTO.setPassword("newPassword");

        assertDoesNotThrow(() -> chefService.updateAccount(chefAccountDTO));

        verify(chefMapper).update(Mockito.any(Chef.class));
    }
}