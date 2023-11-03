package controller;

import com.github.pagehelper.Page;
import com.nus.context.BaseContext;
import com.nus.mapper.ChefMapper;
import com.nus.pojo.dto.ChefDTO;
import com.nus.pojo.dto.ChefPageDTO;
import com.nus.pojo.entity.Chef;
import com.nus.result.PageResult;
import com.nus.service.impl.ChefServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


public class ChefServiceTest {
    @InjectMocks
    private ChefServiceImpl chefService;

    @Mock
    private ChefMapper chefMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveChef() {
        ChefDTO chefDTO = new ChefDTO();
        // 设置chefDTO的属性

//        Mockito.when(BaseContext.getCurrentId()).thenReturn(1L); // 模拟BaseContext.getCurrentId()返回1L
        chefDTO.setId(BaseContext.getCurrentId());
        chefService.save(chefDTO);

        // 使用Mockito验证chefMapper的insert方法是否被调用
        Mockito.verify(chefMapper, Mockito.times(1)).insert(Mockito.any(Chef.class));
    }

    @Test
    public void testUpdateChef() {
        ChefDTO chefDTO = new ChefDTO();
        // 设置chefDTO的属性

//        Mockito.when(BaseContext.getCurrentId()).thenReturn(1L); // 模拟BaseContext.getCurrentId()返回1L
        chefDTO.setId(BaseContext.getCurrentId());
        chefService.update(chefDTO);

        // 使用Mockito验证chefMapper的update方法是否被调用
        Mockito.verify(chefMapper, Mockito.times(1)).update(Mockito.any(Chef.class));
    }

    @Test
    public void testPageQuery() {
        ChefPageDTO chefPageDTO = new ChefPageDTO();
        // 设置chefPageDTO的属性

        // 模拟chefMapper的pageQuery方法返回一个空的Page对象
        Page<Chef> emptyPage = new Page<>();
        Mockito.when(chefMapper.pageQuery(chefPageDTO)).thenReturn(emptyPage);

        PageResult result = chefService.page(chefPageDTO);

        // 使用断言验证返回的PageResult是否符合预期
        Assertions.assertEquals(0, result.getTotal());
        Assertions.assertTrue(result.getRecords().isEmpty());
    }

    @Test
    public void testStartOrStopChef() {
        Integer status = 1;
        Long chefId = 1L;

        chefService.startOrStop(status, chefId);

        Chef chef = new Chef();
        chef.setId(chefId);
        chef.setStatus(status);

        // 使用Mockito验证chefMapper的update方法是否被调用，并检查传递给update方法的参数
        Mockito.verify(chefMapper, Mockito.times(1)).update(Mockito.eq(chef));
    }

    @Test
    public void testGetChefById() {
        Long chefId = 1L;
        Chef chef = new Chef();
        // 设置chef的属性

        // 模拟chefMapper的getById方法返回一个特定的Chef对象
        Mockito.when(chefMapper.getById(chefId)).thenReturn(chef);

        Chef result = chefService.getById(chefId);

        // 使用断言验证返回的Chef对象是否符合预期
        Assertions.assertEquals(chef, result);
    }
}
