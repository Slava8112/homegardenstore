package telran.org.scotlandyard.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telran.org.scotlandyard.entity.Category;
import telran.org.scotlandyard.entity.Favorite;
import telran.org.scotlandyard.exception.CategoryNotFoundException;
import telran.org.scotlandyard.repository.FavoriteReposit;

import java.util.List;
@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService{

    private static final Logger log = LoggerFactory.getLogger(FavoriteServiceImpl.class);
    @Autowired
    private FavoriteReposit favoriteReposit;

    @Override
    public Favorite createFavorite(Long user_out_id, Favorite favorite) {
        log.debug("Favorite is created {}", favorite);
        Favorite unit = favoriteReposit.save(favorite);
        return unit;
    }

    @Override
    public Favorite UpdateFavorite(Favorite favorite) {
        return null;
    }

    @Override
    public Favorite findById(Long id) {
        Favorite unit = favoriteReposit.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("No category with id " + id));
        return unit;
    }

    @Override
    public List<Favorite> getAllFavorites() {
        return favoriteReposit.findAll();
    }

    @Override
    public void delete(Long id) {
        Favorite unit = findById(id);
        favoriteReposit.delete(unit);
    }
}
