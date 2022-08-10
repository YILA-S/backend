package backend.services.manager.infra;

import org.springframework.data.repository.CrudRepository;

public interface MongoManagerRepository extends CrudRepository<ManagerModel, String> {
    ManagerModel findByEmail(String email);
}
