package backend.services.courses.infra;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoSectionRepository extends MongoRepository<SectionModel, String> {
}
