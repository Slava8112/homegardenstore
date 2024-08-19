package telran.org.scotlandyard.service.converter;

public interface Converter<Entity, Dto, CreateDto> {

    Entity toEntity(CreateDto dto);

    Dto toDto(Entity entity);
}

