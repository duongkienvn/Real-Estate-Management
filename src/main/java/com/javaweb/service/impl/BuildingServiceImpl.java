package com.javaweb.service.impl;

import com.javaweb.converter.BuildingConverter;
import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
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

import java.util.ArrayList;
import java.util.List;

@Service
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
        List<AssignmentBuildingEntity> assignmentBuildingEntities = buildingEntity.getAssignmentBuildingEntities();
        List<UserEntity> assignmentStaff = new ArrayList<>();
        for (AssignmentBuildingEntity building : assignmentBuildingEntities) {
            if (building.getId().equals(buildingId)) {
                assignmentStaff.add(building.getUserEntity());
            }
        }
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
        buildingRepository.save(building);
    }

    @Override
    public void updateBuilding(Long id) {
        BuildingEntity building = buildingRepository.findBuildingEntityById(id);
    }

    @Override
    public void deleteBuilding(List<Long> id) {
        buildingRepository.deleteAllById(id);
    }
}
