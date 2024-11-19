package com.javaweb.repository;

import com.javaweb.entity.AssignmentBuildingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentBuildingRepository extends JpaRepository<AssignmentBuildingEntity, Long> {
    List<AssignmentBuildingEntity> findAssignmentBuildingEntitiesByBuildingId(Long id);
    void deleteAssignmentBuildingEntitiesByBuildingIdIn(List<Long> BuildingIdList);
    void deleteAssignmentBuildingEntitiesByBuildingIdAndUserEntityIdIn(Long BuildingId, List<Long> staffIdList);
}
