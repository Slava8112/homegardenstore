package telran.org.de.scotlandyard.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import telran.org.de.scotlandyard.entity.Favorite;
import telran.org.de.scotlandyard.entity.Order;
import telran.org.de.scotlandyard.entity.Product;
import telran.org.de.scotlandyard.entity.UserEntity;
import telran.org.de.scotlandyard.exception.FavoriteNotFoundException;
import telran.org.de.scotlandyard.exception.ProductIllegalArgumentException;
import telran.org.de.scotlandyard.repository.FavoriteRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {

    private static final Logger log = LoggerFactory.getLogger(FavoriteServiceImpl.class);
    private final UserService userService;
    private final FavoriteRepository favoriteRepository;
    private final ProductService productService;

    @Override
    public Favorite createFavorite(Long userEntityId, Long productId) {
//        log.debug("Favorite is created {}", favorite);

        UserEntity user = userService.getById(userEntityId);
        Product product = productService.getById(productId);
        favoriteRepository.findByUserEntityIdAndProductId(userEntityId, productId).ifPresent(f -> {
            throw new ProductIllegalArgumentException("This product is already in favorites");
        });
        Favorite favorite = new Favorite();
        favorite.setUserEntity(user);
        favorite.setProduct(product);
        Favorite savedFavorite = favoriteRepository.save(favorite);
        return savedFavorite;
    }

    @Override
    public List<Favorite> getUsersFavoritesByUserId(Long userId) {
        return favoriteRepository.findAllByUserEntityId(userId);
    }

    @Override
    public List<Favorite> getAllFavorites() {
        return favoriteRepository.findAll();
    }

    @Override
    public List<Favorite> getFavoritesByCurrentUser() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        UserEntity userEntity = userService.findByEmail(userEmail);
        List<Favorite> favorites = favoriteRepository.findAllByUserEntity(userEntity);

        return favorites;
    }


    @Override
    @Transactional
    public void delete(Long userId, Long productId) {
        log.debug("Attempting to remove product with ID: {} from favorites for user with ID: {}", productId, userId);
        boolean exists = favoriteRepository.existsByUserEntityIdAndProductId(userId, productId);
        if (!exists) {
            throw new FavoriteNotFoundException("Favorites not found");
        }
        favoriteRepository.deleteByUserEntityIdAndProductId(userId, productId);
    }
}
