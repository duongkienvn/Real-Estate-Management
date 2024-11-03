package com.javaweb.service.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
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

    @Override
    public ResponseDTO listStaffs(Long buildingId) {
        ResponseDTO result = new ResponseDTO();
        BuildingEntity buildingEntity = buildingRepository.findById(buildingId).get();
        System.out.println(buildingEntity.getName());
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
}
