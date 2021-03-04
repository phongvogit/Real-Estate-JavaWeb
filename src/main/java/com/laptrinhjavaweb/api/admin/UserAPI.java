package com.laptrinhjavaweb.api.admin;


import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.dto.response.ResponseDTO;
import com.laptrinhjavaweb.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController(value = "userAPIOfAdmin")
@RequestMapping(value = "/api/user")
public class UserAPI {

    @Autowired
    private UserService userService;

    @GetMapping("/{customerId}/customers")
    public ResponseDTO loadCustomers(@PathVariable("customerId") Long id){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(userService.loadStaffByCustomerId(id));
        responseDTO.setMessage("success");
        responseDTO.setDetail("");
        return responseDTO;
    }
    @PostMapping
    public ResponseDTO createNewUser(@RequestBody UserDTO userDTO){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(userService.save(userDTO));
        responseDTO.setMessage("success");
        return responseDTO;
    }
    @PutMapping
    public ResponseDTO updateNewUser(@RequestBody UserDTO userDTO){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(userService.save(userDTO));
        responseDTO.setMessage("success");
        return responseDTO;
    }

    @DeleteMapping
    public ResponseDTO deleteUser(@RequestBody Long[] ids){
        ResponseDTO responseDTO = new ResponseDTO();
        userService.delete(ids);
        responseDTO.setMessage("success");
        return responseDTO;
    }
}
