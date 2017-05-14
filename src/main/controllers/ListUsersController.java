package main.controllers;

import main.model.pojo.UsersInformation;
import main.services.UserServiceInterface;
import main.services.UsersInformationInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by admin on 13.05.2017.
 */
@Controller
public class ListUsersController {
    private   UsersInformationInterface informationService;
    private UserServiceInterface userService;

    @Autowired
    public ListUsersController(UsersInformationInterface service, UserServiceInterface userService) {
        this.informationService = service;
        this.userService = userService;
    }

    @RequestMapping(value="/admin**", method = RequestMethod.GET)
    public ModelAndView actionsWithListUsers(Model model, HttpSession session, HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        mav.addObject("usersInformations", informationService.getAll());
        mav.addObject("userService", userService.getAll());
        mav.addObject("title", "Admin page");
        mav.setViewName("listUsers");
        return mav;
    }

    @RequestMapping(value="/blocking/{usersId}", method = RequestMethod.GET)
    public String blockUser(@PathVariable("usersId") String usersId){

        toBlockOrUnblock(usersId,0);
        return "redirect:/admin";
    }

    @RequestMapping(value="/unblocking/{usersId}", method = RequestMethod.GET)
    public String unblockUser(@PathVariable("usersId") String usersId){

        toBlockOrUnblock(usersId,1);
        return "redirect:/admin";
    }


    private void toBlockOrUnblock(String id, Integer enable){
        if (id.matches("\\d+")) {
            userService.blockUnblockUser(Integer.parseInt(id),enable);
        }
    }
}
