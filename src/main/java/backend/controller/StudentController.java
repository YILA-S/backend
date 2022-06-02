package backend.controller;

import backend.domain.student.IStudentRepository;
import backend.domain.student.Student;
import backend.domain.student.StudentFactory;
import backend.exception.InvalidParameterException;
import backend.infra.hibernate.student.HibernateStudentRepo;
import backend.infra.hibernate.student.StudentModelAssembler;
import backend.ui.student.StudentRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    StudentFactory studentFactory = new StudentFactory();
    IStudentRepository studentRepository = new HibernateStudentRepo();
    StudentModelAssembler studentModelAssembler = new StudentModelAssembler();

    @PostMapping("/student")
    @ResponseStatus(code = HttpStatus.CREATED)
    public String createStudent(@RequestBody StudentRequest studentRequest) throws Exception {
        validateStudentRequest(studentRequest);

        Student newStudent = studentFactory.createStudent(
                studentRequest.firstName, studentRequest.lastName, studentRequest.birthDate,
                studentRequest.email, studentRequest.phone, studentRequest.address);


        studentRepository.save(studentModelAssembler.createStudentModel(newStudent));

        return newStudent.getId();
    }

    private void validateStudentRequest(StudentRequest studentRequest) throws Exception{

        if(studentRequest.birthDate == null || studentRequest.firstName == null || studentRequest.lastName == null){
            throw new InvalidParameterException("Student should have firstname, lastName and birthDate");
        } else if (studentRequest.phone == null || studentRequest.email == null) {
            throw new InvalidParameterException("Student should have email or phone number");
        }
    }

}
