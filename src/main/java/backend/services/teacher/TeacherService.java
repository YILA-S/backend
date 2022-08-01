package backend.services.teacher;

import backend.services.role.Role;
import backend.services.teacher.infra.MongoTeacherRepository;
import backend.services.teacher.infra.TeacherModel;
import backend.services.teacher.infra.TeacherModelAssembler;
import backend.services.appuser.domain.AppUser;
import backend.services.appuser.domain.AppUserFactory;
import backend.ui.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("teacherService")
public class TeacherService {
    @Autowired
    private MongoTeacherRepository teacherRepository;
    private TeacherModelAssembler teacherModelAssembler = new TeacherModelAssembler();
    AppUserFactory userFactory = new AppUserFactory();

    public AppUser create(UserRequest userRequest) throws Exception {

        AppUser newUser = userFactory.create(
                userRequest.lastName, userRequest.birthDate, userRequest.email,
                userRequest.phone, userRequest.address, userRequest.firstName, userRequest.password
        );
        newUser.addRole(Role.Teacher);
        TeacherModel newUserModel = teacherModelAssembler.createTeacherModel(newUser);

        teacherRepository.save(newUserModel);

        return newUser;
    }

    public TeacherModel findById(String studentId) {
        return teacherRepository.findById(studentId).get();

    }

    public void deleteAll(){
        teacherRepository.deleteAll();
    };

    public void deleteById(String userId){
        teacherRepository.deleteById(userId);
    }

}
