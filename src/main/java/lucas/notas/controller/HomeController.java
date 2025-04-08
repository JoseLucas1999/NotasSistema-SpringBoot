package lucas.notas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "home"; // vai procurar por home.jsp ou home.html dependendo do seu setup
    }
    
    @RequestMapping("/backHome")
    public String homeBacking() {
        return "home";
    }

    @RequestMapping("/indexBack")
    public String indexBacking() {
        return "index";
    }
}
