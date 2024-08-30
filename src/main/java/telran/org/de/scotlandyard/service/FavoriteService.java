package telran.org.de.scotlandyard.service;

import telran.org.de.scotlandyard.entity.Favorite;

import java.util.List;

public interface FavoriteService {

    Favorite createFavorite(Long UserEntity_id, Favorite favorite);

    Favorite UpdateFavorite(Favorite favorite);

    Favorite findById(Long id);

    List<Favorite> getAllFavorites();

    void delete(Long id);
}
