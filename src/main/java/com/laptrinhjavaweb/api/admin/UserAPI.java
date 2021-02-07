package com.laptrinhjavaweb.api.admin;


import com.laptrinhjavaweb.dto.response.ResponseDTO;
import com.laptrinhjavaweb.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
