package booking.controller;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import booking.model.User;


import booking.repository.UserRepository;

 
@Controller
public class AppController {

    
	@GetMapping(value = {"/"})
    public String homepage() {
        return "redirect:/login";
    }

    @GetMapping("/home")
    public String hello() {
        return "home";
    }
    

       
}
