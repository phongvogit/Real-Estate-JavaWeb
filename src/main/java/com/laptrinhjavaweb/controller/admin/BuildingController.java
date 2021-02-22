package com.laptrinhjavaweb.controller.admin;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.request.BuildingSearchRequestDto;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;

@Controller(value = "buildingControllerOfAdmin")
public class BuildingController {
    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private IBuildingService buildingService;

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/admin/building-list", method = RequestMethod.GET)
    public ModelAndView buildingList(@ModelAttribute("modelSearch") BuildingSearchRequestDto model) {
        ModelAndView mav = new ModelAndView("admin/building-list");

        mav.addObject("buildingResponse", buildingService.findAll(model));
        mav.addObject("staffmaps", userService.getStaffMaps());
        mav.addObject("districtmaps", buildingService.getAllDistricts());
        mav.addObject("buildingTypeMaps", buildingService.getAllBuildingTypes());

        return mav;
    }

    @RequestMapping(value = "/admin/building-edit/{buildingId}", method = RequestMethod.GET)
    public ModelAndView buildingUpdate(@PathVariable("buildingId") Long id, @RequestParam(value = "message", required = false) String message) {
        ModelAndView mav = new ModelAndView("admin/building-edit");
        mav.addObject("modelSearch", buildingService.findBuildingById(id));
        mav.addObject("staffmaps", userService.getStaffMaps());
        mav.addObject("districtmaps", buildingService.getAllDistricts());
        mav.addObject("buildingTypeMaps", buildingService.getAllBuildingTypes());
        if(message != null && !message.equals("")){
            mav.addObject("message", messageUtil.getMessage(message));
        }
        return mav;
    }

    @RequestMapping(value = "/admin/building-view/{buildingId}", method = RequestMethod.GET)
    public ModelAndView getBuilding(@PathVariable("buildingId") Long id) {
        ModelAndView mav = new ModelAndView("admin/building-view");
        mav.addObject("building", buildingService.findBuildingById(id));
        mav.addObject("buildingTypeMaps", buildingService.getAllBuildingTypes());
        return mav;
    }
}