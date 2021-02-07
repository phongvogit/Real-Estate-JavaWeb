package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.response.ResponseDTO;
import com.laptrinhjavaweb.service.impl.BuildingService;
import com.laptrinhjavaweb.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController(value = "buildingAPIOfAdmin")
@RequestMapping(value = "/api/building")
public class BuildingAPI {

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseDTO createBuilding(@RequestBody BuildingDTO newBuilding){
        ResponseDTO responseDTO = new ResponseDTO();
        BuildingDTO  res = null;
        try {
            res = buildingService.saveNewBuilding(newBuilding);
        }catch (Exception e){
            System.out.println(e);
        }
        responseDTO.setData(res);
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

    @PutMapping()
    public ResponseDTO updateBuilding(@RequestBody BuildingDTO buildingDTO){
        ResponseDTO responseDTO = new ResponseDTO();

        responseDTO.setData(buildingService.saveNewBuilding(buildingDTO));
        responseDTO.setMessage("success");
        return responseDTO;
    }

    @DeleteMapping
    public ResponseDTO deleteBuilding(@RequestBody BuildingDTO buildingDTO){
        ResponseDTO responseDTO = new ResponseDTO();

        buildingService.deleteBuilding(buildingDTO.getIds());
        responseDTO.setMessage("success");
        return responseDTO;
    }
}
