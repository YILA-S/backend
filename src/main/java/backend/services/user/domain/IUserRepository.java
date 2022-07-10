package backend.services.user.domain;

import backend.services.user.infra.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserModel, String> {
}
