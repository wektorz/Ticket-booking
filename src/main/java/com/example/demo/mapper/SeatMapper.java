package com.example.demo.mapper;

import com.example.demo.entity.SeatEntity;
import com.example.demo.model.SeatModel;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

import java.util.Optional;

@Component
public class SeatMapper implements BasicMapper<SeatEntity, SeatModel> {

    @Override
    public Optional<SeatEntity> toEntity(SeatModel model) {
        if (model == null) {
            return Optional.empty();
        }

        ModelMapper modelMapper = new ModelMapper();
        SeatEntity entity = modelMapper.map(model, SeatEntity.class);
        Optional<SeatEntity> mappedEntity = Optional.of(entity);

        return mappedEntity;
    }

    @Override
    public Optional<SeatModel> toModel(SeatEntity entity) {

        if (entity == null) {
            return Optional.empty();
        }

        ModelMapper modelMapper = new ModelMapper();
        SeatModel model = modelMapper.map(entity, SeatModel.class);
        Optional<SeatModel> mappedModel = Optional.of(model);

        return mappedModel;
    }
}
