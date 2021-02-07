package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.response.ResponseDTO;
import com.laptrinhjavaweb.service.impl.CustomerService;
import com.laptrinhjavaweb.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController(value = "customerAPIOfAdmin")
@RequestMapping(value = "/api/customer")
public class CustomerAPI {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserService userService;

    @GetMapping("/{customerid}/staffs")
    public ResponseDTO loadStaff(@PathVariable("customerid") Long id){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(userService.loadStaffByCustomerId(id));
        responseDTO.setMessage("success");
        return responseDTO;
    }

    @PostMapping
    public ResponseDTO createCustomer(@RequestBody CustomerDTO model){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(customerService.saveNewCustomer(model));
        responseDTO.setMessage("success");
        responseDTO.setDetail("");
        return responseDTO;
    }

    @PutMapping
    public ResponseDTO updateCustomer(@RequestBody CustomerDTO model){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(customerService.saveNewCustomer(model));
        responseDTO.setMessage("success");
        responseDTO.setDetail("");
        return responseDTO;
    }

    @DeleteMapping
    public ResponseDTO deleteCustomer(@RequestBody CustomerDTO model){
        ResponseDTO responseDTO = new ResponseDTO();

        customerService.deleteCustomer(model.getIds());
        responseDTO.setMessage("success");
        return responseDTO;
    }
}
