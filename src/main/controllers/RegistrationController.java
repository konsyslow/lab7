package main.controllers;

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
    @Autowired
    private  UsersInformationInterface usersInformationService;
    @Autowired
    private  UserServiceInterface userService;

    @RequestMapping(value="/reg",method = RequestMethod.POST)
    public ModelAndView registration(@RequestParam(name="firstName", required = false) String firstName,
                                         @RequestParam(name="secondName", required = false) String secondName,
                                         @RequestParam(name="lastName", required = false) String lastName,
                                         @RequestParam(name="login", required = false) String login,
                                         @RequestParam(name="password", required = false) String password,
                                         Model model) {
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
            mav.setViewName("redirect:/");
        }
        return mav;
    }
}
