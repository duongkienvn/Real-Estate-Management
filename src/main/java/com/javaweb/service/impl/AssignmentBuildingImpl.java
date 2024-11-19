package com.javaweb.service.impl;

import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.repository.AssignmentBuildingRepository;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.AssignmentBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AssignmentBuildingImpl implements AssignmentBuildingService {
    @Autowired
    private AssignmentBuildingRepository assignmentBuildingRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void assigningBuilding(AssignmentBuildingDTO assignmentBuildingDTO) {
        List<Long> staffId = assignmentBuildingDTO.getStaffs();
        List<AssignmentBuildingEntity> assignmentBuildingEntities = new ArrayList<>();

        BuildingEntity building = buildingRepository
                .findById(assignmentBuildingDTO.getBuildingId()).get();

        List<AssignmentBuildingEntity> assignmentBuildingEntityList = assignmentBuildingRepository
                .findAssignmentBuildingEntitiesByBuildingId(building.getId());

        if (assignmentBuildingEntityList.isEmpty()) {
            for (Long id : staffId) {
                AssignmentBuildingEntity assignmentBuilding = setAssignmentBuilding(building, id);
                assignmentBuildingEntities.add(assignmentBuilding);
            }
        } else {
            Set<Long> staffIdSet = assignmentBuildingEntityList.stream()
                    .map(key -> key.getUserEntity().getId())
                    .collect(Collectors.toSet());

            for (Long id : staffId) {
                if (!staffIdSet.contains(id)) {
                    AssignmentBuildingEntity assignmentBuildingEntity = setAssignmentBuilding(building, id);
                    assignmentBuildingEntities.add(assignmentBuildingEntity);
                }
            }

            List<Long> removedStaffId = new ArrayList<>();
            for (Long id : staffIdSet) {
                if (!staffId.contains(id)) {
                    removedStaffId.add(id);
                }
            }
            assignmentBuildingRepository
                    .deleteAssignmentBuildingEntitiesByBuildingIdAndUserEntityIdIn(assignmentBuildingDTO.getBuildingId(), removedStaffId);
        }

        assignmentBuildingRepository.saveAll(assignmentBuildingEntities);
    }

    private AssignmentBuildingEntity setAssignmentBuilding(BuildingEntity building, Long staffId) {
        AssignmentBuildingEntity assignmentBuilding = new AssignmentBuildingEntity();
        UserEntity userEntity = userRepository.findById(staffId).get();
        assignmentBuilding.setBuilding(building);
        assignmentBuilding.setUserEntity(userEntity);

        return assignmentBuilding;
    }
}
