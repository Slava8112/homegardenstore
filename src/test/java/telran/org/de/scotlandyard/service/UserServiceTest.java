package telran.org.de.scotlandyard.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import telran.org.de.scotlandyard.entity.UserEntity;
import telran.org.de.scotlandyard.exception.NoUniqueUserEmailException;
import telran.org.de.scotlandyard.repository.UserRepository;
import java.util.Optional;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        List<UserEntity> mockUsers = List.of(new UserEntity(), new UserEntity());
        when(userRepository.findAll()).thenReturn(mockUsers);

        List<UserEntity> users = userService.getAll();

        assertNotNull(users);
        assertEquals(2, users.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetById_Success() {
        UserEntity mockUser = new UserEntity();
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));

        UserEntity user = userService.getById(1L);

        assertNotNull(user);
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testGetById_NotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> userService.getById(1L));
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testCreate_Success() {
        UserEntity mockUser = new UserEntity();
        when(userRepository.save(mockUser)).thenReturn(mockUser);

        UserEntity user = userService.create(mockUser);

        assertNotNull(user);
        verify(userRepository, times(1)).save(mockUser);
    }

    @Test
    void testCreate_EmailExists() {
        UserEntity mockUser = new UserEntity();
        mockUser.setEmail("testuser@example.com");

        when(userRepository.findByEmail("testuser@example.com")).thenReturn(Optional.of(mockUser));

        assertThrows(NoUniqueUserEmailException.class, () -> userService.create(mockUser));
        verify(userRepository, times(1)).findByEmail("testuser@example.com");
    }
}
