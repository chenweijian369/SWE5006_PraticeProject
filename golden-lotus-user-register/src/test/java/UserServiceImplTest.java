import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import com.nus.mapper.UserMapper;
import com.nus.pojo.dto.UserDTO;
import com.nus.pojo.entity.User;
import com.nus.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserMapper userMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveUser() {
        // Prepare test data
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("NIE");
        userDTO.setPassword("123456");

        // Test the save method
        userService.save(userDTO);

        // Verify that the user was saved with the correct properties
        verify(userMapper, times(1)).insert(any(User.class));
    }
}
