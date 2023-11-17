package yc.mhkif.marjaneapi.Controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "marjane/api/v1")
public class HomeController {
    @GetMapping()
    public String sayHello() {
        return "Hola, Mundo!";
    }
}
