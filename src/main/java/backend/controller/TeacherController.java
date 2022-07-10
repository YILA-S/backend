package backend.controller;

import backend.exception.InvalidParameterException;
import backend.exception.ItemNotFoundException;
import backend.services.teacher.TeacherService;
import backend.services.teacher.infra.TeacherModel;
import backend.services.user.domain.User;
import backend.ui.UserRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Optional;

@RestController
public class TeacherController {
    @Resource(name = "teacherService")
    private TeacherService teacherService;

    @PostMapping("/teacher")
    @ResponseStatus(code = HttpStatus.CREATED)
    public User createTeacher(@RequestBody UserRequest userRequest) throws Exception {
        validateTeacherRequest(userRequest);
        return teacherService.create(userRequest);
    }

    @GetMapping("/teacher/{id}")
    @ResponseStatus(code = HttpStatus.FOUND)
    public TeacherModel findTeacherById(@PathVariable("id") String teacherId){
        var teacher = teacherService.findById(teacherId);
        if(teacher == null) {
            throw new ItemNotFoundException(String.format("Teacher with Id : %s not found", teacherId));
        }
        return teacher;
    }

    @DeleteMapping("/teacher")
    @ResponseStatus(code = HttpStatus.OK)
    public String deleteAllTeacher(){
        teacherService.deleteAll();
        return "All teacher deleted";
    }

    private void validateTeacherRequest(UserRequest userRequest) throws Exception{

        if(userRequest.birthDate == null || userRequest.firstName == null || userRequest.lastName == null){
            throw new InvalidParameterException("Teacher should have firstname, lastName and birthDate");
        } else if (userRequest.phone == null || userRequest.email == null) {
            throw new InvalidParameterException("Teacher should have email or phone number");
        }
    }

}
