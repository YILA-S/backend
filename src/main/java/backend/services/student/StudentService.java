package backend.services.student;

import backend.exception.ItemNotFoundException;
import backend.services.role.Role;
import backend.services.student.domain.Student;
import backend.services.student.infra.MongoStudentRepo;
import backend.services.student.infra.StudentModel;
import backend.services.student.infra.StudentModelAssembler;
import backend.services.appuser.domain.AppUser;
import backend.services.appuser.domain.AppUserFactory;
import backend.ui.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service("studentService")
@Qualifier("studentDetailService")
public class StudentService implements UserDetailsService{
    @Autowired
    private MongoStudentRepo studentRepository;
    private StudentModelAssembler userModelAssembler = new StudentModelAssembler();
    private AppUserFactory userFactory = new AppUserFactory();

    public AppUser create(UserRequest userRequest) throws Exception {

        AppUser newUser = userFactory.create(
                userRequest.lastName, userRequest.birthDate, userRequest.email, userRequest.phone,
                userRequest.address, userRequest.firstName, userRequest.password
        );
        newUser.addRole(Role.STUDENT);
        StudentModel newUserModel = userModelAssembler.toStudentModel(newUser);

        studentRepository.save(newUserModel);

        return newUser;
    }

    public Student findById(String studentId) {
        var found = studentRepository.findById(studentId);
        if(found.isEmpty()) throw new ItemNotFoundException(String.format("Student with Id : %s not found", studentId));
        return userModelAssembler.toStudent(found.get());
    }

    public Student findByEmail(String email) {
        var found = studentRepository.findByEmail(email);
        if(found == null) throw new ItemNotFoundException(String.format("Student with email : %s not found", email));
        return userModelAssembler.toStudent(found);
    }

    public void deleteAll(){
        studentRepository.deleteAll();
    }

    public void deleteById(String userId){
        studentRepository.deleteById(userId);
    }

    public List<StudentModel> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Username equivalent to email !!
        Student student = findByEmail(username);
        if(student == null)
            throw new ItemNotFoundException(String.format("User with userName %s not found", username));

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        student.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.name())));

        return new User(student.getEmail(), student.getPassword(), authorities);

    }
}
