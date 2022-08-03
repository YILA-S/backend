package backend.services.teacher;

import backend.exception.ItemNotFoundException;
import backend.services.role.Role;
import backend.services.teacher.domain.Teacher;
import backend.services.teacher.infra.MongoTeacherRepository;
import backend.services.teacher.infra.TeacherModel;
import backend.services.teacher.infra.TeacherModelAssembler;
import backend.services.appuser.domain.AppUser;
import backend.services.appuser.domain.AppUserFactory;
import backend.ui.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;


@Service("teacherService")
public class TeacherService implements UserDetailsService {
    @Autowired
    private MongoTeacherRepository teacherRepository;
    private TeacherModelAssembler teacherModelAssembler = new TeacherModelAssembler();
    AppUserFactory userFactory = new AppUserFactory();

    public AppUser create(UserRequest userRequest) throws Exception {

        AppUser newUser = userFactory.create(
                userRequest.lastName, userRequest.birthDate, userRequest.email,
                userRequest.phone, userRequest.address, userRequest.firstName, userRequest.password
        );
        newUser.addRole(Role.TEACHER);
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Username equivalent to email !!
        Teacher teacher = teacherRepository.findByEmail(username);
        if(teacher == null)
            throw new ItemNotFoundException(String.format("User with userName %s not found", username));

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        teacher.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.name())));

        return new User(teacher.getEmail(), teacher.getPassword(), authorities);

    }
}
