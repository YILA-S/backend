package backend.services.student;

import backend.exception.ItemNotFoundException;
import backend.services.student.domain.Student;
import backend.services.student.infra.MongoStudentRepo;
import backend.services.student.infra.StudentModel;
import backend.services.student.infra.StudentModelAssembler;
import backend.services.user.domain.User;
import backend.services.user.domain.UserFactory;
import backend.ui.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("studentService")
public class StudentService{
    @Autowired
    private MongoStudentRepo studentRepository;
    private StudentModelAssembler userModelAssembler = new StudentModelAssembler();
    private UserFactory userFactory = new UserFactory();

    public User create(UserRequest userRequest) throws Exception {

        User newUser = userFactory.createStudent(
                userRequest.firstName, userRequest.lastName, userRequest.birthDate,
                userRequest.email, userRequest.phone, userRequest.address);
        StudentModel newUserModel = userModelAssembler.toStudentModel(newUser);

        studentRepository.save(newUserModel);

        return newUser;
    }

    public Student findById(String studentId) {
        var finded = studentRepository.findById(studentId);
        if(finded.isEmpty()) throw new ItemNotFoundException(String.format("Student with Id : %s not found", studentId));
        return userModelAssembler.toStudent(finded.get());
    }

    public void deleteAll(){
        studentRepository.deleteAll();
    };

    public void deleteById(String userId){
        studentRepository.deleteById(userId);
    }

    public List<StudentModel> getAllStudents() {
        return studentRepository.findAll();
    }
}
