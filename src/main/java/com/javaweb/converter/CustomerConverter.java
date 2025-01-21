package com.javaweb.converter;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.response.CustomerSearchResonse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CustomerConverter {
    @Autowired
    private ModelMapper modelMapper;

    public CustomerEntity converToCustomer(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = modelMapper.map(customerDTO, CustomerEntity.class);
        customerEntity.setIsActive(1);
        return customerEntity;
    }

    public CustomerSearchResonse convertToCustomerResponse(CustomerEntity customer) {
        CustomerSearchResonse customerSearchResonse = modelMapper.map(customer, CustomerSearchResonse.class);
        return customerSearchResonse;
    }

    public CustomerDTO convertToCustomerDTO(CustomerEntity customer) {
        CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
        return customerDTO;
    }
}
