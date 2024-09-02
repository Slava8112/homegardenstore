package telran.org.de.scotlandyard.converter;

import org.springframework.stereotype.Component;
import telran.org.de.scotlandyard.dto.favoritedto.FavoriteCreateDto;
import telran.org.de.scotlandyard.dto.favoritedto.FavoriteDto;
import telran.org.de.scotlandyard.entity.Favorite;

@Component
public class FavoriteConverter implements Converter<Favorite, FavoriteDto, FavoriteCreateDto> {

    private final ProductConverter productConverter;

    public FavoriteConverter(ProductConverter productConverter) {
        this.productConverter = productConverter;
    }

    @Override
    public Favorite toEntity(FavoriteCreateDto favoriteCreateDto) {
        Favorite favorite = new Favorite();
        return favorite;
    }

    @Override
    public FavoriteDto toDto(Favorite favorite) {
        FavoriteDto dto = new FavoriteDto(favorite.getId(), favorite.getProduct().getId());
        return dto;
    }
}
