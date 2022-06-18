package backend.services.courses.domain;

import backend.services.courses.infra.CourseModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICourseRepository extends JpaRepository<CourseModel, String> {
}
