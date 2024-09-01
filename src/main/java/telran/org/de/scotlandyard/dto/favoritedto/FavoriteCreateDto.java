package telran.org.de.scotlandyard.dto.favoritedto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FavoriteCreateDto {

    private Long favoriteId;
    private Long userEntityId;
    private Long productId;
}
