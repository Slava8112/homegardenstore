package telran.org.de.scotlandyard.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import telran.org.de.scotlandyard.entity.Favorite;
import telran.org.de.scotlandyard.entity.UserEntity;
import telran.org.de.scotlandyard.exception.CategoryNotFoundException;
import telran.org.de.scotlandyard.repository.FavoriteRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {

    private static final Logger log = LoggerFactory.getLogger(FavoriteServiceImpl.class);
    private final UserService userService;
    private final FavoriteRepository favoriteRepository;

    @Override
    public Favorite createFavorite(Long userEntityId, Favorite favorite) {
        log.debug("Favorite is created {}", favorite);
        return favoriteRepository.save(favorite);
    }

    @Override
    public Favorite UpdateFavorite(Favorite favorite) {
        return null;
    }

    @Override
    public Favorite findById(Long id) {
        return favoriteRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("No category with id " + id));
    }

    @Override
    public List<Favorite> getAllFavorites() {
        return favoriteRepository.findAll();
    }

    @Override
    public List<Favorite> getFavoritesByCurrentUser() {
        String userEmail = null;
        UserEntity userEntity = userService.findByEmail(userEmail);
        return List.of();
    }

    @Override
    public void delete(Long id) {
        Favorite unit = findById(id);
        favoriteRepository.delete(unit);
    }
}
