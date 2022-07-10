package backend.controller;

import backend.exception.ItemNotFoundException;
import backend.services.student.StudentService;
import backend.services.student.infra.StudentModel;
import backend.services.user.domain.User;
import backend.exception.InvalidParameterException;
import backend.ui.UserRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Optional;

@RestController
public class StudentController {

    @Resource(name = "studentService")
    private StudentService studentService;

    @PostMapping("/student")
    @ResponseStatus(code = HttpStatus.CREATED)
    public User createStudent(@RequestBody UserRequest userRequest) throws Exception {
        validateStudentRequest(userRequest);
        return studentService.create(userRequest);
    }

    @GetMapping("/student/{id}")
    @ResponseStatus(code = HttpStatus.FOUND)
    public StudentModel findStudentById(@PathVariable("id") String studentId){
        var student = studentService.findById(studentId);
        if(student == null) {
            throw new ItemNotFoundException(String.format("Student with Id : %s not found", studentId));
        }
        return student;
    }

    @DeleteMapping("/student")
    @ResponseStatus(code = HttpStatus.OK)
    public String deleteAllStudent(){
        studentService.deleteAll();
        return "All students deleted";
    }

    private void validateStudentRequest(UserRequest userRequest) throws Exception{

        if(userRequest.birthDate == null || userRequest.firstName == null || userRequest.lastName == null){
            throw new InvalidParameterException("Student should have firstname, lastName and birthDate");
        } else if (userRequest.phone == null || userRequest.email == null) {
            throw new InvalidParameterException("Student should have email and phone number");
        }
    }

}
