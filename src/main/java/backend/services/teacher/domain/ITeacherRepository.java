package backend.services.teacher.domain;

import backend.services.teacher.infra.TeacherModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITeacherRepository extends JpaRepository<TeacherModel, String> {
}
