package telran.org.de.scotlandyard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import telran.org.de.scotlandyard.entity.Favorite;
import telran.org.de.scotlandyard.entity.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    Optional<Favorite> findByUserEntityIdAndProductId(Long userId, Long productId);

    List<Favorite> findAllByUserEntityId(Long userId);

    List<Favorite> findAllByUserEntity(UserEntity userEntity);

    void deleteByUserEntityIdAndProductId(Long userId, Long productId);

    boolean existsByUserEntityIdAndProductId(Long userId, Long productId);
}
