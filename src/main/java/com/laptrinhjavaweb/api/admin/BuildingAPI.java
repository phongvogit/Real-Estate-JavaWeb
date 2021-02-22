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

    @PostMapping
    public ResponseDTO createBuilding(@RequestBody BuildingDTO newBuilding){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(buildingService.saveNewBuilding(newBuilding));
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
