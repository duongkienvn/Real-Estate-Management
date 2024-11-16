package com.javaweb.controller.admin;


import com.javaweb.enums.District;
import com.javaweb.enums.TypeCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.service.BuildingService;
import com.javaweb.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller(value = "buildingControllerOfAdmin")

public class BuildingController {
    @Autowired
    private IUserService service;

    @Autowired
    private BuildingService buildingService;

    @GetMapping("/admin/building-list")
    public ModelAndView buildingList(@ModelAttribute BuildingSearchRequest buildingSearchRequest, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("admin/building/list");
        modelAndView.addObject("modelSearch", buildingSearchRequest);

        List<BuildingSearchResponse> responseList = buildingService.findBuilding(buildingSearchRequest);
        modelAndView.addObject("buildingList", responseList);
        modelAndView.addObject("staffList", service.getStaffs());
        modelAndView.addObject("districts", District.district());
        modelAndView.addObject("typeCode", TypeCode.typeCode());
        return modelAndView;
    }

    @GetMapping("/admin/building-edit")
    public ModelAndView editBuilding(@ModelAttribute("buildingEdit") BuildingDTO buildingDTO, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("admin/building/edit");
        modelAndView.addObject("districts", District.district());
        modelAndView.addObject("typeCodes", TypeCode.typeCode());

        return modelAndView;
    }

    @GetMapping("/admin/building-edit-{id}")
    public ModelAndView buildingEdit(@PathVariable("id") Long id, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("admin/building/edit");
        BuildingDTO buildingDTO = new BuildingDTO();

        modelAndView.addObject("buildingEdit", buildingDTO);
        modelAndView.addObject("districts", District.district());
        modelAndView.addObject("typeCodes", TypeCode.typeCode());
        return modelAndView;
    }
}
