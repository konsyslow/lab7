package main.controllers;

import main.services.PublicationsServiceInterface;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;


/**
 * Created by admin on 30.04.2017.
 */
@Controller
public class ListPublicationsController {
    private static final Logger LOGGER = Logger.getLogger(ListPublicationsController.class.getName());

    private  PublicationsServiceInterface publicationsService;

    @Autowired
    public ListPublicationsController(PublicationsServiceInterface publicationsService) {
        this.publicationsService = publicationsService;
    }

    @RequestMapping(value="/listPublications", method = RequestMethod.GET)
    public ModelAndView actionsWithList(Model model, HttpSession session, HttpServletRequest request){
            ModelAndView mav = new ModelAndView();
            /*String username = ((org.springframework.security.core.userdetails.User)SecurityContextHolder
                                .getContext().getAuthentication().getPrincipal()).getUsername();*/
        String username = request.getUserPrincipal().getName();
        LOGGER.debug("usernaaaaaaaaaaameeee: " + username);
            //Integer userId = Integer.parseInt(session.getAttribute("userId").toString());
            //List<Publications> usersPublications = publicationsService.getUsersPublications(userId);
            model.addAttribute("username1",username);
            model.addAttribute("usersPublications",publicationsService.getByUsername(username));
            model.addAttribute("value", "Hello,user");
        return mav;
    }

    @RequestMapping(value="/delete/{usersPublicationsId}", method = RequestMethod.GET)
    public String deletePublication(@PathVariable("usersPublicationsId") String usersPublicationsId){

        deletePub(usersPublicationsId);
        return "redirect:/listPublications";
    }

    private void deletePub(String id){
        if (id.matches("\\d+")) {
            publicationsService.delete(Integer.parseInt(id));
        }
    }
}
