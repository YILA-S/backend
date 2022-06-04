package backend.controller;

import backend.exception.ItemNotFoundException;
import backend.services.student.StudentService;
import backend.services.student.domain.Student;
import backend.exception.InvalidParameterException;
import backend.services.student.infra.StudentModel;
import backend.ui.student.StudentRequest;
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
    public Student createStudent(@RequestBody StudentRequest studentRequest) throws Exception {
        validateStudentRequest(studentRequest);
        return studentService.createStudent(studentRequest);
    }

    @GetMapping("/student/{id}")
    @ResponseStatus(code = HttpStatus.FOUND)
    public Optional<StudentModel> findStudentById(@PathVariable("id") String studentId){
        var student = studentService.findStudentById(studentId);
        if(student.isEmpty()) {
            throw new ItemNotFoundException(String.format("Student with Id : %s not found", studentId));
        }
        return student;
    }

    private void validateStudentRequest(StudentRequest studentRequest) throws Exception{

        if(studentRequest.birthDate == null || studentRequest.firstName == null || studentRequest.lastName == null){
            throw new InvalidParameterException("Student should have firstname, lastName and birthDate");
        } else if (studentRequest.phone == null || studentRequest.email == null) {
            throw new InvalidParameterException("Student should have email or phone number");
        }
    }
}
