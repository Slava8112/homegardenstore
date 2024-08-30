package telran.org.de.scotlandyard.converter;

public interface Converter<Entity, Dto, CreateDto> {

    Entity toEntity(CreateDto dto);

    Dto toDto(Entity entity);
}

