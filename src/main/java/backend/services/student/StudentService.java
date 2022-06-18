package backend.services.student;

import backend.services.student.domain.IStudentRepository;
import backend.services.student.infra.StudentModel;
import backend.services.student.infra.StudentModelAssembler;
import backend.services.user.domain.User;
import backend.services.user.domain.UserFactory;
import backend.ui.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("studentService")
public class StudentService{
    @Autowired
    private IStudentRepository studentRepository;
    private StudentModelAssembler userModelAssembler = new StudentModelAssembler();
    UserFactory userFactory = new UserFactory();

    public User create(UserRequest userRequest) throws Exception {

        User newUser = userFactory.createStudent(
                userRequest.firstName, userRequest.lastName, userRequest.birthDate,
                userRequest.email, userRequest.phone, userRequest.address);
        StudentModel newUserModel = userModelAssembler.createStudentModel(newUser);

        studentRepository.save(newUserModel);

        return newUser;
    }

    public Optional<StudentModel> findById(String studentId) {
        return studentRepository.findById(studentId);

    }

    public void deleteAll(){
        studentRepository.deleteAll();
    };

    public void deleteById(String userId){
        studentRepository.deleteById(userId);
    }
}
