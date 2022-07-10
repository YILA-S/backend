package backend.services.courses.domain;

import backend.services.courses.infra.SectionModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISectionRepository extends JpaRepository<SectionModel, String> {
}
