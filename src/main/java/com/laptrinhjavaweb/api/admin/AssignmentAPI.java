package com.laptrinhjavaweb.api.admin;


import com.laptrinhjavaweb.dto.request.AssignmentRequestDto;
import com.laptrinhjavaweb.dto.response.ResponseDTO;
import com.laptrinhjavaweb.service.IAssignmentService;
import com.laptrinhjavaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController(value = "assignmentAPIOfAdmin")
@RequestMapping(value = "/api/assignment")
public class AssignmentAPI {

    @Autowired
    private IAssignmentService assignmentService;

    @Autowired
    private IUserService userService;

    @PostMapping("/building")
    public ResponseDTO assignedTask(@RequestBody AssignmentRequestDto model){
        ResponseDTO responseDTO = new ResponseDTO();
        assignmentService.saveAssignmentForBuilding(model.getBuildingId(), model.getStaffIds());
        responseDTO.setMessage("success");
        return responseDTO;
    }
    @GetMapping("/{buildingid}/staffs")
    public ResponseDTO loadStaff(@PathVariable("buildingid") Long id){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(userService.loadStaffByBuildingId(id));
        responseDTO.setMessage("success");
        return responseDTO;
    }
}
