import com.nus.mapper.CategoryMapper;
import com.nus.pojo.vo.CategoryVO;
import com.nus.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryServiceImplTest {
    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryMapper categoryMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testShowAll() {
        // 模拟CategoryMapper的showAll方法返回的数据

        List<CategoryVO> mockCategoryList = new ArrayList<>();

        // 当调用categoryMapper的showAll方法时，返回模拟数据
        Mockito.when(categoryMapper.showAll()).thenReturn(mockCategoryList);

        // 调用被测试的服务方法
        List<CategoryVO> result = categoryService.showAll();

        // 验证返回的结果是否与模拟数据相匹配
        assertEquals(mockCategoryList, result);
}
}
