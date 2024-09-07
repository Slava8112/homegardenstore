package telran.org.de.scotlandyard.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import telran.org.de.scotlandyard.entity.Favorite;
import telran.org.de.scotlandyard.entity.Order;
import telran.org.de.scotlandyard.entity.Product;
import telran.org.de.scotlandyard.entity.UserEntity;
import telran.org.de.scotlandyard.exception.FavoriteNotFoundException;
import telran.org.de.scotlandyard.exception.ProductIllegalArgumentException;
import telran.org.de.scotlandyard.repository.FavoriteRepository;
import telran.org.de.scotlandyard.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FavoriteServiceImplTest {

    @Mock
    private FavoriteRepository favoriteRepository;

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProductService productService;

    @InjectMocks
    private FavoriteServiceImpl favoriteService;

    @Test
    void createFavorite() {
        Long id = 1L;
        Long productId = 1L;
        UserEntity user = new UserEntity();
        Product product = new Product();

        when(userService.getById(id)).thenReturn(user);
        when(productService.getById(productId)).thenReturn(product);
        when(favoriteRepository.findByUserEntityIdAndProductId(id, productId)).thenReturn(Optional.empty());

        favoriteService.createFavorite(id, productId);

        verify(favoriteRepository, times(1)).save(any((Favorite.class)));
    }

    @Test
    void createFavorite_WhenProductAlreadyInFavorite() {
        Long id = 1L;
        Long productId = 1L;

        when(favoriteRepository.findByUserEntityIdAndProductId(id, productId)).thenReturn(Optional.of(new Favorite()));

        assertThatThrownBy(() -> favoriteService.createFavorite(id, productId))
                .isInstanceOf(ProductIllegalArgumentException.class)
                .hasMessageContaining("This product is already in favorites");
        verify(favoriteRepository, never()).save(any(Favorite.class));
    }

    @Test
    void findByUserEntityId() {
        Long userId = 1L;
        List<Favorite> expectedFavorites = new ArrayList<>();
        expectedFavorites.add(new Favorite());
        when(favoriteRepository.findAllByUserEntityId(userId)).thenReturn(expectedFavorites);

        List<Favorite> result = favoriteService.getUsersFavoritesByUserId(userId);

        assertThat(result).isEqualTo(expectedFavorites);
        verify(favoriteRepository, times(1)).findAllByUserEntityId(userId);
    }

    @Test
    void getFavoritesByCurrentUser() {
        SecurityContext securityContext = mock(SecurityContext.class);
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("testuser@example.com");
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        UserEntity fakeUser = new UserEntity();
             when(userService.findByEmail("testuser@example.com")).thenReturn(fakeUser);

            List<Favorite> fakeFavorit = List.of(new Favorite(), new Favorite());

        when(favoriteRepository.findAllByUserEntity(fakeUser)).thenReturn(fakeFavorit);

            List<Favorite> favorites = favoriteService.getFavoritesByCurrentUser();

        assertNotNull(favorites);
        assertEquals(2, favorites.size());
        verify(favoriteRepository, times(1)).findAllByUserEntity(fakeUser);
    }

    @Test
    void delete_WhenProductExist() {
        Long userId = 1L;
        Long productId = 1L;

        when(favoriteRepository.existsByUserEntityIdAndProductId(userId, productId)).thenReturn(true);

        favoriteService.delete(userId, productId);

        verify(favoriteRepository, times(1)).deleteByUserEntityIdAndProductId(userId, productId);
    }

    @Test
    void delete_WhenProductIsAbsent() {
Long userId = 1L;
Long productId = 1L;

when(favoriteRepository.existsByUserEntityIdAndProductId(userId, productId)).thenReturn(false);

assertThatThrownBy(() -> favoriteService.delete(userId, productId))
        .isInstanceOf(FavoriteNotFoundException.class)
        .hasMessageContaining("Favorites not found");

verify(favoriteRepository, never()).deleteByUserEntityIdAndProductId(anyLong(), anyLong());

    }
}