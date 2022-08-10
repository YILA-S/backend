package backend.services.manager;

import backend.exception.ItemNotFoundException;
import backend.services.appuser.domain.AppUser;
import backend.services.appuser.domain.AppUserFactory;
import backend.services.manager.domain.Manager;
import backend.services.manager.infra.ManagerModel;
import backend.services.manager.infra.ManagerModelAssembler;
import backend.services.manager.infra.MongoManagerRepository;
import backend.services.role.Role;
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

@Service("managerService")
@Qualifier("managerDetailService")
public class ManagerServices implements UserDetailsService {

    @Autowired
    private MongoManagerRepository managerRepository;
    private final ManagerModelAssembler managerModelAssembler = new ManagerModelAssembler();
    private final AppUserFactory userFactory = new AppUserFactory();

    public AppUser create(UserRequest userRequest) throws Exception {

        AppUser newUser = userFactory.create(
                userRequest.lastName, userRequest.birthDate, userRequest.email, userRequest.phone,
                userRequest.address, userRequest.firstName, userRequest.password
        );
        newUser.addRole(Role.ADMIN);
        ManagerModel managerModel = managerModelAssembler.toMangerModel(newUser);

        managerRepository.save(managerModel);

        return newUser;
    }

    public Manager findById(String managerId) {
        var found = managerRepository.findById(managerId);
        if(found.isEmpty()) throw new ItemNotFoundException(String.format("Student with Id : %s not found", managerId));
        return managerModelAssembler.toManager(found.get());
    }

    public Manager findByEmail(String email) {
        var found = managerRepository.findByEmail(email);
        if(found == null) throw new ItemNotFoundException(String.format("Student with email : %s not found", email));
        return managerModelAssembler.toManager(found);
    }

    public void deleteAll(){
        managerRepository.deleteAll();
    }

    public void deleteById(String userId){
        managerRepository.deleteById(userId);
    }

    public List<ManagerModel> getAllStudents() {
        return (List<ManagerModel>) managerRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Username equivalent to email !!
        Manager manager = findByEmail(username);
        if(manager == null)
            throw new ItemNotFoundException(String.format("User with userName %s not found", username));

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        manager.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.name())));

        return new User(manager.getEmail(), manager.getPassword(), authorities);
    }
}
