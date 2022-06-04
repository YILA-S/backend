package backend.services.student;

import backend.services.student.domain.IStudentRepository;
import backend.services.student.domain.Student;
import backend.services.student.domain.StudentFactory;
import backend.services.student.infra.StudentModel;
import backend.services.student.infra.StudentModelAssembler;
import backend.ui.student.StudentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("studentService")
public class StudentService {
    @Autowired
    private IStudentRepository studentRepository;
    private StudentModelAssembler studentModelAssembler = new StudentModelAssembler();
    StudentFactory studentFactory = new StudentFactory();

    public Student createStudent(StudentRequest studentRequest) throws Exception {

        Student newStudent = studentFactory.createStudent(
                studentRequest.firstName, studentRequest.lastName, studentRequest.birthDate,
                studentRequest.email, studentRequest.phone, studentRequest.address);
        StudentModel newStudentModel = studentModelAssembler.createStudentModel(newStudent);

        studentRepository.save(newStudentModel);

        return newStudent;
    }

    public Optional<StudentModel> findStudentById(String studentId) {
        return studentRepository.findById(studentId);
    }
}
