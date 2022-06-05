package backend.services.teacher;

import backend.services.user.IUserService;
import backend.services.user.domain.IUserRepository;
import backend.services.user.domain.User;
import backend.services.user.domain.UserFactory;
import backend.services.user.infra.UserModel;
import backend.services.user.infra.UserModelAssembler;
import backend.ui.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("teacherService")
public class TeacherService implements IUserService {
    @Autowired
    private IUserRepository studentRepository;
    private UserModelAssembler userModelAssembler = new UserModelAssembler();
    UserFactory userFactory = new UserFactory();

    public User create(UserRequest userRequest) throws Exception {

        User newUser = userFactory.createStudent(
                userRequest.firstName, userRequest.lastName, userRequest.birthDate,
                userRequest.email, userRequest.phone, userRequest.address);
        UserModel newUserModel = userModelAssembler.createStudentModel(newUser);

        studentRepository.save(newUserModel);

        return newUser;
    }

    public Optional<UserModel> findById(String studentId) {
        return studentRepository.findById(studentId);

    }

    public void deleteAll(){
        studentRepository.deleteAll();
    };

    public void deleteById(String userId){
        studentRepository.deleteById(userId);
    }

}
