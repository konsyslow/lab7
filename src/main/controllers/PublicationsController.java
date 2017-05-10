package main.controllers;

import main.model.pojo.Publications;
import main.model.pojo.Users;
import main.services.PublicationsServiceInterface;
import main.services.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by admin on 01.05.2017.
 */
@Controller
public class PublicationsController {

    private PublicationsServiceInterface publicationsService;
    private UserServiceInterface userService;

    @Autowired
    public PublicationsController(PublicationsServiceInterface publicationsService, UserServiceInterface userService) {
        this.publicationsService = publicationsService;
        this.userService = userService;
    }

    @RequestMapping(value="/publications",method = RequestMethod.GET)
    public ModelAndView showPublications(@RequestParam(name="id", required = false) String id,
                                        Model model, HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        String user_id = "", name = "", genre = "", text="";

        if ((id != null) && (id.matches("\\d+"))) {
            model.addAttribute("pubid",id);
            Publications publication = publicationsService.get(Integer.parseInt(id));
            if (publication != null){
                name = publication.getName();
                genre = publication.getGenre();
                text = publication.getText();
            }

        }
        String username = request.getUserPrincipal().getName();
        Users user = userService.getUserByLogin(username);

        model.addAttribute("name",name);
        model.addAttribute("genre",genre);
        model.addAttribute("text",text);
        model.addAttribute("username1",username);
        model.addAttribute("userid",user.getId());
        return mav;
    }

    @RequestMapping(value="/publications",method = RequestMethod.POST)
    public ModelAndView actionsWithPublications(@RequestParam(name="id", required = false) String id,
                                         @RequestParam(name="name", required = false) String name,
                                         @RequestParam(name="genre", required = false) String genre,
                                         @RequestParam(name="text", required = false) String text,
                                                HttpSession session, HttpServletRequest request) {

        ModelAndView mav = new ModelAndView();
        try {
            //Integer userId = Integer.parseInt(session.getAttribute("userId").toString());
            String username = request.getUserPrincipal().getName();
            Users user = userService.getUserByLogin(username);

            if ((id == null) || ("null".equals(id))) {
                publicationsService.insert(user.getId(), name, genre, text);
            } else {
                Publications publication = publicationsService.get(Integer.parseInt(id));
                publication.setUser_id(user.getId());
                publication.setName(name);
                publication.setGenre(genre);
                publication.setText(text);
                publicationsService.update(publication);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        mav.setViewName("redirect:/listPublications");
        return mav;
    }
}
