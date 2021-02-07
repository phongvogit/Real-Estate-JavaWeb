package com.laptrinhjavaweb.controller.admin;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.service.ICustomerService;
import com.laptrinhjavaweb.service.ITransactionService;
import com.laptrinhjavaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller(value = "userControllerOfAdmin")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private ITransactionService transactionService;

    @RequestMapping(value = "/admin/customer-list", method = RequestMethod.GET)
    public ModelAndView customerList(@ModelAttribute("modelSearch") CustomerDTO model) {
        ModelAndView mav = new ModelAndView("admin/customer-list");
        mav.addObject("customers", customerService.findAll(model));
        return mav;
    }
    @RequestMapping(value = "/admin/customer-edit", method = RequestMethod.GET)
    public ModelAndView customerEdit (@ModelAttribute("modelSearch") CustomerDTO model) {
        ModelAndView mav = new ModelAndView("admin/customer-edit");
        mav.addObject("modelSearch", model);
        if(model.getId() != null){
            mav.addObject("modelSearch", customerService.findCustomerById(model.getId()));
            mav.addObject("transactionResponse", transactionService.getAllByCustomerId(model.getId()));
        }

        return mav;
    }
}
