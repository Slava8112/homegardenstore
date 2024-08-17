package telran.org.scotlandyard.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import telran.org.scotlandyard.entity.Favorite;
import telran.org.scotlandyard.exception.CategoryNotFoundException;
import telran.org.scotlandyard.repository.FavoriteRepository;

import java.util.List;
@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService{

    private static final Logger log = LoggerFactory.getLogger(FavoriteServiceImpl.class);

    private final FavoriteRepository favoriteRepository;

    @Override
    public Favorite createFavorite(Long userEntityId, Favorite favorite) {
        log.debug("Favorite is created {}", favorite);
        Favorite unit = favoriteRepository.save(favorite);
        return unit;
    }

    @Override
    public Favorite UpdateFavorite(Favorite favorite) {
        return null;
    }

    @Override
    public Favorite findById(Long id) {
        Favorite unit = favoriteRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("No category with id " + id));
        return unit;
    }

    @Override
    public List<Favorite> getAllFavorites() {
        return favoriteRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        Favorite unit = findById(id);
        favoriteRepository.delete(unit);
    }
}
