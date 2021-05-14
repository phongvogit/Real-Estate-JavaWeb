package com.laptrinhjavaweb.controller.admin;

import com.laptrinhjavaweb.security.utils.SecurityUtils;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "userControllerOfAdmin")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private MessageUtil messageUtil;

    @RequestMapping(value = "/admin/user/list", method = RequestMethod.GET)
    public ModelAndView userList() {
        ModelAndView mav = new ModelAndView("admin/user/list");
        mav.addObject("model", userService.findAll());
        mav.addObject("authority", userService.getHighestAuthority());
        return mav;
    }

    @RequestMapping(value = "/admin/user/edit", method = RequestMethod.GET)
    public ModelAndView add(@RequestParam(value = "message", required = false) String message) {
        ModelAndView mav = new ModelAndView("admin/user/edit");
        mav.addObject("roleMaps", userService.getRoles());
        if(message != null && !message.equals("")){
            mav.addObject("message", messageUtil.getMessage(message));
        }
        return mav;
    }

    @RequestMapping(value = "/admin/user/edit/{userId}", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable("userId") Long id, @RequestParam(value = "message", required = false) String message) {
        ModelAndView mav = new ModelAndView("admin/user/edit");
        mav.addObject("model", userService.findOneByUserId(id));
        if(message != null && !message.equals("")){
            mav.addObject("message", messageUtil.getMessage(message));
        }
        return mav;
    }

    @RequestMapping(value = "/admin/user/view/{userId}", method = RequestMethod.GET)
    public ModelAndView view(@PathVariable("userId") Long id) {
        ModelAndView mav = new ModelAndView("admin/user/view");
        mav.addObject("user", userService.findOneByUserId(id));
        return mav;
    }
}
