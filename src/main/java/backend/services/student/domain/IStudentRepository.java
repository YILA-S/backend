package backend.services.student.domain;

import backend.services.student.infra.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStudentRepository extends JpaRepository<StudentModel, String> {
}
