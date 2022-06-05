package backend.services.user.infra;

import backend.services.user.domain.User;

public class UserModelAssembler {

    public UserModel createStudentModel(User user){
        UserModel userModel = new UserModel();

        userModel.id = user.getId();
        userModel.firstName = user.getFirstName();
        userModel.lastName = user.getLastName();
        userModel.address = user.getAddress();
        userModel.birthDate = user.getBirthDate();
        userModel.email = user.getEmail();
        userModel.phone = user.getPhone();

        return userModel;
    }
}
