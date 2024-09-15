package telran.org.de.scotlandyard.service;

import telran.org.de.scotlandyard.entity.Favorite;

import java.util.List;

public interface FavoriteService {

    Favorite createFavorite(Long UserEntity_id, Long ProductId);

    List<Favorite> getUsersFavoritesByUserId(Long userId);

    List<Favorite> getAllFavorites();

    List<Favorite> getFavoritesByCurrentUser();

    void delete(Long id, Long productId);
}
