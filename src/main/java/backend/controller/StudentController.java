package backend.controller;

import backend.domain.student.Student;
import backend.domain.student.StudentFactory;
import backend.ui.student.StudentRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    StudentFactory studentFactory = new StudentFactory();

    @PostMapping("/student")
    @ResponseStatus(code = HttpStatus.CREATED)
    public String welcome(@RequestBody StudentRequest studentRequest) throws Exception {

        Student newStudent = studentFactory.createStudent(
                studentRequest.firstName, studentRequest.lastName, studentRequest.birthDate,
                studentRequest.email, studentRequest.phone, studentRequest.address);
        return newStudent.getId();
    }

    private void validateStudentRequest(StudentRequest studentRequest){

        if(studentRequest.birthDate == null || studentRequest.firstName == null || studentRequest.birthDate == null){
            // raise corresponding error
        }
    }

}
