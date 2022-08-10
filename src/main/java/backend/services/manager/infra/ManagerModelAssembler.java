package backend.services.manager.infra;

import backend.services.appuser.domain.AppUser;
import backend.services.manager.domain.Manager;

public class ManagerModelAssembler {

    public ManagerModel toMangerModel(AppUser user){
        ManagerModel managerModel = new ManagerModel();

        managerModel.id = user.getId();
        managerModel.firstName = user.getFirstName();
        managerModel.lastName = user.getLastName();
        managerModel.address = user.getAddress();
        managerModel.birthDate = user.getBirthDate();
        managerModel.email = user.getEmail();
        managerModel.phone = user.getPhone();
        managerModel.roles = user.getRoles();
        managerModel.password = user.getPassword();

        return managerModel;
    }

    public Manager toManager(ManagerModel managerModel) {
        Manager manager = new Manager(managerModel.id, managerModel.firstName, managerModel.birthDate,
                managerModel.lastName, managerModel.email, managerModel.phone, managerModel.address, managerModel.password);
        manager.setRoles(managerModel.roles);
        return manager;
    }
}
