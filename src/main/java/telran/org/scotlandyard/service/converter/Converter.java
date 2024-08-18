package telran.org.scotlandyard.service.converter;

import org.springframework.stereotype.Component;

public interface Converter<Entity, Dto, CreateDto> {

        Entity toEntity(CreateDto dto);

        Dto toDto(Entity entity);
    }

