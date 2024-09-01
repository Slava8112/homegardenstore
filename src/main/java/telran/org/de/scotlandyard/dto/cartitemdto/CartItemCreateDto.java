package telran.org.de.scotlandyard.dto.cartitemdto;

public record CartItemCreateDto(
        Long productId,
        int quantity
) {
}
