package com.example.demo.mapper;

import com.example.demo.entity.RoomEntity;
import com.example.demo.model.RoomModel;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

import java.util.Optional;

@Component
public class RoomMapper implements BasicMapper<RoomEntity, RoomModel> {

    @Override
    public Optional<RoomEntity> toEntity(RoomModel model) {
        if (model == null) {
            return Optional.empty();
        }

        ModelMapper modelMapper = new ModelMapper();
        RoomEntity entity = modelMapper.map(model, RoomEntity.class);
        Optional<RoomEntity> mappedEntity = Optional.of(entity);

        return mappedEntity;
    }

    @Override
    public Optional<RoomModel> toModel(RoomEntity entity) {

        if (entity == null) {
            return Optional.empty();
        }

        ModelMapper modelMapper = new ModelMapper();
        RoomModel model = modelMapper.map(entity, RoomModel.class);
        Optional<RoomModel> mappedModel = Optional.of(model);

        return mappedModel;
    }
}