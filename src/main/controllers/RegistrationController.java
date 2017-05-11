package main.controllers;

import main.model.pojo.Roles;
import main.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by admin on 01.05.2017.
 */
@Controller
public class RegistrationController {

    private  UsersInformationInterface usersInformationService;
    private  UserServiceInterface userService;
    private RolesServiceInterface rolesService;

    @Autowired
    public RegistrationController(UsersInformationInterface usersInformationService, UserServiceInterface userService,
                                  RolesServiceInterface rolesService) {
        this.usersInformationService = usersInformationService;
        this.userService = userService;
        this.rolesService = rolesService;
    }

    @RequestMapping(value="/reg",method = RequestMethod.GET)
    public String showReg(Model model){
        return "reg";
    }

    @RequestMapping(value="/reg",method = RequestMethod.POST)
    public ModelAndView registration(@RequestParam(name="firstName", required = false) String firstName,
                                         @RequestParam(name="secondName", required = false) String secondName,
                                         @RequestParam(name="lastName", required = false) String lastName,
                                         @RequestParam(name="login", required = false) String login,
                                         @RequestParam(name="password", required = false) String password) {
        ModelAndView mav = new ModelAndView();
        String hash_pass = null;
        if(firstName=="" && secondName=="" && lastName=="" &&
                login=="" && password=="") {
            mav.setViewName("redirect:/reg");
        }else{
            try {
                hash_pass = PasswordStorage.createHash(password);
            }catch(PasswordStorage.CannotPerformOperationException e){
                e.printStackTrace();
            }
            userService.insert(login,hash_pass);
            usersInformationService.insert(firstName,secondName,lastName);
            rolesService.insertRole(login,"ROLE_ADMIN");
            mav.setViewName("redirect:/login");
        }
        return mav;
    }
/*
    @RequestMapping(value = "/reg", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }
        model.setViewName("reg");

        return model;

    }*/
}
