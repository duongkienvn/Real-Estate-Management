package com.javaweb.service.impl;

import com.javaweb.converter.CustomerConverter;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResonse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerConverter customerConverter;

    @Override
    public List<CustomerSearchResonse> findCustomer(CustomerSearchRequest customerSearchRequest) {
        List<CustomerSearchResonse> customerSearchResonses = new ArrayList<>();
        List<CustomerEntity> customerEntities = customerRepository.findCustomer(customerSearchRequest);
        customerEntities
                .forEach(customer -> customerSearchResonses.add(customerConverter.convertToCustomerResponse(customer)));
        return customerSearchResonses;
    }

    @Override
    public ResponseDTO staffList(Long customerId) {
        ResponseDTO responseDTO = new ResponseDTO();
        CustomerEntity customerEntity = customerRepository.findById(customerId).get();
        List<UserEntity> staffList = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        List<UserEntity> assignedStaff = customerEntity.getUserEntities();
        List<StaffResponseDTO> staffResponseDTOS = new ArrayList<>();

        for (UserEntity staff : staffList) {
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setFullName(staff.getFullName());
            staffResponseDTO.setStaffId(staff.getId());
            if (assignedStaff.contains(staff)) {
                staffResponseDTO.setChecked("checked");
            } else {
                staffResponseDTO.setChecked("");
            }
            staffResponseDTOS.add(staffResponseDTO);
        }

        responseDTO.setData(staffResponseDTOS);
        responseDTO.setMessage("success!");

        return responseDTO;
    }

    @Override
    public void addCustomer(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = customerConverter.converToCustomer(customerDTO);
        customerRepository.save(customerEntity);
    }

    @Override
    public void updateCustomer(CustomerDTO customerDTO) {

    }

    @Override
    public void deleteCustomer(List<Long> customerIdList) {
        if (customerIdList.isEmpty() || customerIdList == null) {
            return;
        }
        List<CustomerEntity> customerEntities = customerRepository.findCustomerEntitiesByIdIn(customerIdList);
        customerEntities.forEach(customer -> customer.setIsActive(0));
    }

    @Override
    public void assignCustomer(AssignmentCustomerDTO assignmentCustomerDTO) {
        CustomerEntity customerEntity = customerRepository.findById(assignmentCustomerDTO.getCustomerId()).get();
        List<UserEntity> userEntities = userRepository.findUserEntitiesByIdIn(assignmentCustomerDTO.getStaffs());
        customerEntity.setUserEntities(userEntities);
        customerRepository.save(customerEntity);
    }

    @Override
    public CustomerDTO findCustomer(Long id) {
        CustomerEntity customerEntity = customerRepository.findById(id).get();
        CustomerDTO customerDTO = customerConverter.convertToCustomerDTO(customerEntity);
        return customerDTO;
    }
}
