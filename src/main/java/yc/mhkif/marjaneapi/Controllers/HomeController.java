package yc.mhkif.marjaneapi.Controllers;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "marjane/api/v1")
public class HomeController {
    @GetMapping()
    public String sayHello() {
        return "Hola, Mundo!";
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String sayHello(@RequestBody String Name){
        return "Hola "+ Name + ", Como estas";
    }
}
