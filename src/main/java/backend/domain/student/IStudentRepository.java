package backend.domain.student;

import backend.infra.hibernate.student.StudentModel;

public interface IStudentRepository {
    void save(StudentModel student);
    void find(String studentId);
    void delete(String studentId);
}
