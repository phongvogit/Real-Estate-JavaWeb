package com.laptrinhjavaweb.controller.admin;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.request.BuildingSearchRequestDto;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "buildingControllerOfAdmin")
public class BuildingController {

    @Autowired
    private IBuildingService buildingService;

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/admin/building-list", method = RequestMethod.GET)
    public ModelAndView buildingList(@ModelAttribute("modelSearch") BuildingSearchRequestDto model) {
        ModelAndView mav = new ModelAndView("admin/building-list");

        mav.addObject("buildings", buildingService.findAll(model));
        mav.addObject("staffmaps", userService.getStaffMaps());
        mav.addObject("districtmaps", buildingService.getAllDistricts());
        mav.addObject("buildingTypeMaps", buildingService.getAllBuildingTypes());

        return mav;
    }

    @RequestMapping(value = "/admin/building-edit/{buildingId}", method = RequestMethod.GET)
    public ModelAndView buildingUpdate(@ModelAttribute("modelSearch") BuildingDTO buildingDTO, @PathVariable("buildingId") Long id) {
        ModelAndView mav = new ModelAndView("admin/building-edit");
        if(id != null && id != -1)
            mav.addObject("modelSearch", buildingService.findBuildingById(id));

        mav.addObject("staffmaps", userService.getStaffMaps());
        mav.addObject("districtmaps", buildingService.getAllDistricts());
        mav.addObject("buildingTypeMaps", buildingService.getAllBuildingTypes());
        return mav;
    }
}