package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.BuildingSearchResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BuildingConverter {
    @Autowired
    private ModelMapper modelMapper;

    public BuildingSearchResponse convertToBuildingResponse(BuildingEntity building) {
        BuildingSearchResponse buildingSearchResponse = modelMapper
                .map(building, BuildingSearchResponse.class);
        buildingSearchResponse.setAddress(building.getDistrict()
                + ", " + building.getWard()
                + ", " + building.getStreet());

        List<RentAreaEntity> rentAreaEntities = building.getRentAreaEntities();
        String rentArea = rentAreaEntities.stream()
                .map(key -> key.getValue().toString())
                .collect(Collectors.joining(","));
        buildingSearchResponse.setRentArea(rentArea);

        return buildingSearchResponse;
    }

    public BuildingEntity convertToBuildingEntity(BuildingDTO buildingDTO) {
        BuildingEntity building = modelMapper.map(buildingDTO, BuildingEntity.class);
        List<String> typeCode = buildingDTO.getTypeCode();
        String type = typeCode.stream().collect(Collectors.joining(","));
        building.setType(type);

        return building;
    }

    public BuildingDTO convertToBuildingDTO(BuildingEntity building) {
        BuildingDTO buildingDTO = modelMapper.map(building, BuildingDTO.class);
        String type = building.getType().replaceAll("\\s+", "");
        String[] typeCode = type.split(",");

        List<RentAreaEntity> rentAreaEntities = building.getRentAreaEntities();
        String rentArea = rentAreaEntities.stream()
                .map(key -> key.getValue().toString())
                .collect(Collectors.joining(","));
        buildingDTO.setRentArea(rentArea);


        buildingDTO.setTypeCode(new ArrayList<>(Arrays.asList(typeCode)));

        return buildingDTO;
    }
}
