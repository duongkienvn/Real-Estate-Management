package com.javaweb.api.admin;

import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.service.CustomerService;
import com.javaweb.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerAPI {
    @Autowired
    private CustomerService customerService;

    @PostMapping
    public void addOrUpdateCustomer(@RequestBody CustomerDTO customerDTO) {
        if (customerDTO.getId() == null) {
            customerService.addCustomer(customerDTO);
        } else {
            customerService.updateCustomer(customerDTO);
        }
    }

    @GetMapping("/{id}/staffs")
    public ResponseDTO loadStaffs(@PathVariable Long id) {
        ResponseDTO responseDTO = customerService.staffList(id);
        return responseDTO;
    }

    @DeleteMapping("/{ids}")
    public void deleteCustomer(@PathVariable List<Long> ids) {
        customerService.deleteCustomer(ids);
    }

    @PostMapping("/assignment")
    public void assignCustomer(@RequestBody AssignmentCustomerDTO assignmentCustomerDTO) {
        customerService.assignCustomer(assignmentCustomerDTO);
    }
}
