package com.javaweb.service.impl;

import com.javaweb.converter.BuildingConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BuildingConverter buildingConverter;

    @Override
    public ResponseDTO listStaffs(Long buildingId) {
        ResponseDTO result = new ResponseDTO();
        BuildingEntity buildingEntity = buildingRepository.findById(buildingId).get();
        List<UserEntity> staffList = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        List<UserEntity> assignmentStaff = buildingEntity.getUserEntities();

        List<StaffResponseDTO> staffResponseDTOS = new ArrayList<>();

        for (UserEntity userEntity : staffList) {
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setFullName(userEntity.getFullName());
            staffResponseDTO.setStaffId(userEntity.getId());
            if (assignmentStaff.contains(userEntity)) {
                staffResponseDTO.setChecked("checked");
            } else {
                staffResponseDTO.setChecked("");
            }
            staffResponseDTOS.add(staffResponseDTO);
        }

        result.setData(staffResponseDTOS);
        result.setMessage("success!");

        return result;
    }

    @Override
    public List<BuildingSearchResponse> findBuilding(BuildingSearchRequest buildingSearchRequest) {
        List<BuildingSearchResponse> result = new ArrayList<>();

        List<BuildingEntity> buildingEntities = buildingRepository.findBuilding(buildingSearchRequest);
        for (BuildingEntity building : buildingEntities) {
            result.add(buildingConverter.convertToBuildingResponse(building));
        }

        return result;
    }

    @Override
    public void addBuilding(BuildingDTO buildingDTO) {
        BuildingEntity building = buildingConverter.convertToBuildingEntity(buildingDTO);
        List<RentAreaEntity> rentAreaEntities = addRentArea(buildingDTO, building);
        building.setRentAreaEntities(rentAreaEntities);
        buildingRepository.save(building);
    }

    @Override
    public BuildingDTO findBuildingById(Long id) {
        BuildingEntity building = buildingRepository.findBuildingEntityById(id);
        return buildingConverter.convertToBuildingDTO(building);
    }

    @Override
    public void updateBuilding(BuildingDTO buildingDTO) {
        BuildingEntity existingBuilding = buildingRepository.findById(buildingDTO.getId())
                .orElseThrow(() -> new RuntimeException("Building not found with ID: " + buildingDTO.getId()));
        existingBuilding = buildingConverter.convertToBuildingEntity(buildingDTO);
        List<RentAreaEntity> rentAreaEntityList = addRentArea(buildingDTO, existingBuilding);
        existingBuilding.setRentAreaEntities(rentAreaEntityList);

        buildingRepository.save(existingBuilding);
    }

    private List<RentAreaEntity> addRentArea(BuildingDTO buildingDTO, BuildingEntity building) {
        String rentArea = buildingDTO.getRentArea();
        String[] rentAreaList = null;
        String rentAreaTemp = rentArea.replaceAll(" ", "");
        if (rentAreaTemp.contains(",")) {
            rentAreaList = rentAreaTemp.split(",");
        } else {
            rentAreaList = rentAreaTemp.split(" ");
        }
        List<RentAreaEntity> rentAreaEntities = new ArrayList<>();
        for (String value : rentAreaList) {
            RentAreaEntity rentAreaEntity = new RentAreaEntity();
            rentAreaEntity.setBuilding(building);
            rentAreaEntity.setValue(Long.parseLong(value));
            rentAreaEntities.add(rentAreaEntity);
        }

        return rentAreaEntities;
    }

    @Override
    public void deleteBuilding(List<Long> buildingIdList) {
        if (buildingIdList == null || buildingIdList.isEmpty()) {
            return;
        }
        buildingRepository.deleteAllById(buildingIdList);
    }

    @Override
    public void assignBuilding(AssignmentBuildingDTO assignmentBuildingDTO) {
        BuildingEntity building = buildingRepository.findBuildingEntityById(assignmentBuildingDTO.getBuildingId());
        List<Long> staffIdList = assignmentBuildingDTO.getStaffs();
        List<UserEntity> userEntities = userRepository.findUserEntitiesByIdIn(staffIdList);
        building.setUserEntities(userEntities);
        buildingRepository.save(building);
    }
}
