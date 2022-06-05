package backend.services.user;

import backend.services.user.domain.User;
import backend.services.user.infra.UserModel;
import backend.ui.UserRequest;

import java.util.Optional;

public interface IUserService {
    User create(UserRequest userRequest) throws Exception;
    Optional<UserModel> findById(String userId);
    void deleteById(String userId);
    void deleteAll();
}
