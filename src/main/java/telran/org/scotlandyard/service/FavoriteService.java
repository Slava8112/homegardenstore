package telran.org.scotlandyard.service;

import telran.org.scotlandyard.entity.Favorite;

import java.util.List;

public interface FavoriteService {

    Favorite createFavorite(Long user_out_id, Favorite favorite);

    Favorite UpdateFavorite(Favorite favorite);

    Favorite findById(Long id);

    List<Favorite> getAllFavorites();

    void delete(Long id);
}
