package com.laptrinhjavaweb.controller.admin;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.service.ICustomerService;
import com.laptrinhjavaweb.service.ITransactionService;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller(value = "customerControllerOfAdmin")
public class CustomerController {

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private ITransactionService transactionService;

    @RequestMapping(value = "/admin/customer-list", method = RequestMethod.GET)
    public ModelAndView customerList(@ModelAttribute("modelSearch") CustomerDTO model) {
        ModelAndView mav = new ModelAndView("admin/customer-list");
        mav.addObject("customerResponse", customerService.findAll(model));
        return mav;
    }
    @RequestMapping(value = "/admin/customer-edit", method = RequestMethod.GET)
    public ModelAndView add (@RequestParam(value = "message", required = false) String message) {
        ModelAndView mav = new ModelAndView("admin/customer-edit");
        mav.addObject("modelSearch", new CustomerDTO());
        if(message != null && !message.equals("")){
            mav.addObject("message", messageUtil.getMessage(message));
        }
        return mav;
    }

    @RequestMapping(value = "/admin/customer-edit/{buildingId}", method = RequestMethod.GET)
    public ModelAndView update (@PathVariable("buildingId") Long id, @RequestParam(value = "message", required = false) String message) {
        ModelAndView mav = new ModelAndView("admin/customer-edit");
        mav.addObject("modelSearch", customerService.findCustomerById(id));
        mav.addObject("transactionResponse", transactionService.getAllByCustomerId(id));
        if(message != null && !message.equals("")){
            mav.addObject("message", messageUtil.getMessage(message));
        }
        return mav;
    }

    @RequestMapping(value = "/admin/customer-view/{customerId}", method = RequestMethod.GET)
    public ModelAndView customerView (@PathVariable("customerId") Long id) {
        ModelAndView mav = new ModelAndView("admin/customer-view");
        mav.addObject("modelSearch", customerService.findCustomerById(id));
        mav.addObject("transactionResponse", transactionService.getAllByCustomerId(id));
        return mav;
    }
}
