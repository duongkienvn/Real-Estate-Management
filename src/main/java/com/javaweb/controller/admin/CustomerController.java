package com.javaweb.controller.admin;

import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResonse;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.CustomerService;
import com.javaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private IUserService userService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/admin/customer-list")
    public ModelAndView customerList(@ModelAttribute CustomerSearchRequest customerSearchRequest, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("admin/customer/list");
        modelAndView.addObject("modelSearch", customerSearchRequest);
        modelAndView.addObject("staffList", userService.getStaffs());

        if (SecurityUtils.getAuthorities().contains("ROLE_STAFF")) {
            Long staffId = SecurityUtils.getPrincipal().getId();
            customerSearchRequest.setStaffId(staffId);
            modelAndView.addObject("customerList", customerService.findCustomer(customerSearchRequest));
        } else {
            modelAndView.addObject("customerList", customerService.findCustomer(customerSearchRequest));
        }

        return modelAndView;
    }

    @GetMapping("/admin/customer-edit")
    public ModelAndView customerEdit(@ModelAttribute("customerEdit") CustomerDTO customerDTO, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("admin/customer/edit");
        return modelAndView;
    }

    @GetMapping("/admin/customer-edit-{id}")
    public ModelAndView customerEdit(@PathVariable("id") Long id, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("admin/customer/edit");
        CustomerDTO customerDTO = customerService.findCustomer(id);
        modelAndView.addObject("customerEdit", customerDTO);
        return modelAndView;
    }
}
