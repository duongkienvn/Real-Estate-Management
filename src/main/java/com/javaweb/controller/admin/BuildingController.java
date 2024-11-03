package com.javaweb.controller.admin;


import com.javaweb.enums.District;
import com.javaweb.enums.TypeCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
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

@Controller(value = "buildingControllerOfAdmin")

public class BuildingController {
    @Autowired
    private IUserService service;

    //    @RequestMapping(value = "/admin/building-list", method = RequestMethod.GET)
    @GetMapping("/admin/building-list")
    public ModelAndView buildingList(@ModelAttribute BuildingSearchRequest buildingSearchRequest, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("admin/building/list");
        modelAndView.addObject("modelSearch", buildingSearchRequest);

        // xuong db lay du lieu xong roi
        List<BuildingSearchResponse> responseList = new ArrayList<>();
        BuildingSearchResponse item1 = new BuildingSearchResponse();

        item1.setId(3L);
        item1.setName("kien");
        item1.setAddress("dfjjfd");
        item1.setNumberOfBasement(2L);
        item1.setManagerName("duong kien");
        item1.setManagerPhone("3343434343");
        item1.setFloorArea(5L);
        item1.setRentArea("110, 200, 300");

        responseList.add(item1);

        BuildingSearchResponse item2 = new BuildingSearchResponse();
        item2.setName("copng kien");
        item2.setId(2L);
        item2.setAddress("dajerjejre");
        item2.setNumberOfBasement(4L);
        item2.setFloorArea(5L);
        item2.setRentArea("110, 200, 300");
        item2.setManagerName("duong kien");
        item2.setManagerPhone("3343434343");
        responseList.add(item2);
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
        buildingDTO.setId(id);
        buildingDTO.setName("kien");
        modelAndView.addObject("buildingEdit", buildingDTO);
        modelAndView.addObject("districts", District.district());
        modelAndView.addObject("typeCodes", TypeCode.typeCode());
        return modelAndView;
    }
}
