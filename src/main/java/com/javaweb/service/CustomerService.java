package com.javaweb.service;


import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResonse;
import com.javaweb.model.response.ResponseDTO;

import java.util.List;

public interface CustomerService {
    List<CustomerSearchResonse> findCustomer(CustomerSearchRequest customerSearchRequest);
    ResponseDTO staffList(Long customerId);
    void addCustomer(CustomerDTO customerDTO);
    void updateCustomer(CustomerDTO customerDTO);
    void deleteCustomer(List<Long> customerIdList);
    void assignCustomer(AssignmentCustomerDTO assignmentCustomerDTO);
    CustomerDTO findCustomer(Long id);
}
