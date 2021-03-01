package com.laptrinhjavaweb.controller.admin;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.PageDTO;
import com.laptrinhjavaweb.dto.request.BuildingSearchRequestDto;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller(value = "buildingControllerOfAdmin")
public class BuildingController {
    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private IBuildingService buildingService;

    @Autowired
    private IUserService userService;

    @RequestMapping(value = {"/admin/building-list","/admin/building-assignment"}, method = RequestMethod.GET)
    public ModelAndView buildingList(@ModelAttribute("modelSearch") BuildingSearchRequestDto model, HttpServletRequest request) {
          ModelAndView mav = new ModelAndView("admin/building-list");
        model.setUrlMapping(request.getServletPath());
        mav.addObject("buildingResponse", buildingService.findAll(model));
        mav.addObject("staffmaps", userService.getStaffMaps());
        mav.addObject("districtmaps", buildingService.getAllDistricts());
        mav.addObject("buildingTypeMaps", buildingService.getAllBuildingTypes());
        mav.addObject("urlMapping", request.getServletPath());
        return mav;
    }

    @RequestMapping(value = "/admin/building-edit", method = RequestMethod.GET)
    public ModelAndView add(@RequestParam(value = "message", required = false) String message) {
        ModelAndView mav = new ModelAndView("admin/building-edit");
        mav.addObject("modelSearch", new BuildingDTO());
        mav.addObject("staffmaps", userService.getStaffMaps());
        mav.addObject("districtmaps", buildingService.getAllDistricts());
        mav.addObject("buildingTypeMaps", buildingService.getAllBuildingTypes());
        if(message != null && !message.equals("")){
            mav.addObject("message", messageUtil.getMessage(message));
        }
        return mav;
    }
    @RequestMapping(value = "/admin/building-edit/{buildingId}", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable("buildingId") Long id, @RequestParam(value = "message", required = false) String message) {
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
    @RequestMapping(value = "/admin/priority/list", method = RequestMethod.GET)
    public ModelAndView showBuildingPriority(@ModelAttribute("model")PageDTO page) {
        ModelAndView mav = new ModelAndView("admin/priority/list");
        mav.addObject("buildingResponse", buildingService.findAllBuildingPriorities(page));
        return mav;
    }
}