package main.controllers;

import main.model.pojo.Publications;
import main.services.PublicationsServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by admin on 01.05.2017.
 */
@Controller
public class PublicationsController {
    @Autowired
    private PublicationsServiceInterface publicationsService;

    @RequestMapping(value="/publications",method = RequestMethod.GET)
    public ModelAndView showPublications(@RequestParam(name="id", required = false) String id,
                                        Model model){
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

        model.addAttribute("name",name);
        model.addAttribute("genre",genre);
        model.addAttribute("text",text);
        return mav;
    }

    @RequestMapping(value="/publications",method = RequestMethod.POST)
    public ModelAndView actionsWithPublications(@RequestParam(name="id", required = false) String id,
                                         @RequestParam(name="name", required = false) String name,
                                         @RequestParam(name="genre", required = false) String genre,
                                         @RequestParam(name="text", required = false) String text,
                                         Model model, HttpSession session) {

        ModelAndView mav = new ModelAndView();
        try {
            Integer userId = Integer.parseInt(session.getAttribute("userId").toString());

            if ((id == null) || ("null".equals(id))) {
                publicationsService.insert(userId, name, genre, text);
            } else {
                Publications publication = publicationsService.get(Integer.parseInt(id));
                publication.setUser_id(userId);
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
