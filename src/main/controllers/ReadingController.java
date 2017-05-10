package main.controllers;

import main.model.pojo.Publications;
import main.services.PublicationsServiceInterface;
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
public class ReadingController {

    private PublicationsServiceInterface publicationsService;

    @Autowired
    public ReadingController(PublicationsServiceInterface publicationsService) {
        this.publicationsService = publicationsService;
    }

    @RequestMapping(value="/reading",method = RequestMethod.GET)
    public ModelAndView reading(@RequestParam(name="id", required = false) String publicationId,
                                         Model model){
        ModelAndView mav = new ModelAndView();
        String user_id = "", name = "", genre = "", text="";

        if ((publicationId != null) && (publicationId.matches("\\d+"))) {
            model.addAttribute("id",publicationId);
            Publications publication = publicationsService.get(Integer.parseInt(publicationId));
            if (publication != null){
                name = publication.getName();
                text = publication.getText();
            }

        }

        model.addAttribute("name",name);
        model.addAttribute("text",text);
        return mav;
    }
}
