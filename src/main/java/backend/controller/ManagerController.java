package backend.controller;

import backend.exception.InvalidParameterException;
import backend.exception.ItemNotFoundException;
import backend.services.appuser.domain.AppUser;
import backend.services.manager.ManagerServices;
import backend.ui.UserRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class ManagerController {

    @Resource(name = "managerService")
    private ManagerServices managerServices;

    @PostMapping("/manager")
    @ResponseStatus(code = HttpStatus.CREATED)
    public AppUser createManager(@RequestBody UserRequest userRequest) throws Exception {
        validateRequest(userRequest);
        return managerServices.create(userRequest);
    }

    @GetMapping("/manager/{id}")
    @ResponseStatus(code = HttpStatus.FOUND)
    public AppUser findManagerById(@PathVariable("id") String mangerId){
        var manager = managerServices.findById(mangerId);
        if(manager == null) {
            throw new ItemNotFoundException(String.format("Manager with Id : %s not found", mangerId));
        }
        return manager;
    }

    @DeleteMapping("/manager")
    @ResponseStatus(code = HttpStatus.OK)
    public String deleteAllManager() {
        managerServices.deleteAll();
        return "All managers deleted";
    }

    private void validateRequest(UserRequest userRequest) throws Exception{

        if(userRequest.birthDate == null || userRequest.firstName == null || userRequest.lastName == null){
            throw new InvalidParameterException("Teacher should have firstname, lastName and birthDate");
        } else if (userRequest.phone == null || userRequest.email == null) {
            throw new InvalidParameterException("Teacher should have email or phone number");
        }
    }

}
