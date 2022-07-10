package backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

    @GetMapping("/")
    @ResponseStatus(code = HttpStatus.OK)
    public String welcome() {
        return "Welcome to yila's school management api !";
    }
}
