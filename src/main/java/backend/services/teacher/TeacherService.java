package backend.services.teacher;

import backend.services.teacher.domain.ITeacherRepository;
import backend.services.teacher.infra.TeacherModel;
import backend.services.teacher.infra.TeacherModelAssembler;
import backend.services.user.domain.User;
import backend.services.user.domain.UserFactory;
import backend.ui.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("teacherService")
public class TeacherService {
    private ITeacherRepository teacherRepository;
    private TeacherModelAssembler teacherModelAssembler = new TeacherModelAssembler();
    UserFactory userFactory = new UserFactory();

    public User create(UserRequest userRequest) throws Exception {

        User newUser = userFactory.createStudent(
                userRequest.firstName, userRequest.lastName, userRequest.birthDate,
                userRequest.email, userRequest.phone, userRequest.address);
        TeacherModel newUserModel = teacherModelAssembler.createTeacherModel(newUser);

        teacherRepository.save(newUserModel);

        return newUser;
    }

    public TeacherModel findById(String studentId) {
        return teacherRepository.findById(studentId);

    }

    public void deleteAll(){
        teacherRepository.deleteAll();
    };

    public void deleteById(String userId){
        teacherRepository.deleteById(userId);
    }

}
