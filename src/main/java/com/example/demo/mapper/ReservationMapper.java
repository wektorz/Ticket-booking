package com.example.demo.mapper;

import com.example.demo.entity.ReservationEntity;
import com.example.demo.model.ReservationModel;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

import java.util.Optional;

@Component
public class ReservationMapper implements BasicMapper<ReservationEntity, ReservationModel> {

    @Override
    public Optional<ReservationEntity> toEntity(ReservationModel model) {
        if (model == null) {
            return Optional.empty();
        }

        ModelMapper modelMapper = new ModelMapper();
        ReservationEntity entity = modelMapper.map(model, ReservationEntity.class);
        Optional<ReservationEntity> mappedEntity = Optional.of(entity);

        return mappedEntity;
    }

    @Override
    public Optional<ReservationModel> toModel(ReservationEntity entity) {

        if (entity == null) {
            return Optional.empty();
        }

        ModelMapper modelMapper = new ModelMapper();
        ReservationModel model = modelMapper.map(entity, ReservationModel.class);
        Optional<ReservationModel> mappedModel = Optional.of(model);

        return mappedModel;
    }
}