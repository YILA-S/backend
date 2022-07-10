package backend.services.student.domain;

import backend.services.student.infra.StudentModel;

public interface IStudentRepository{
    StudentModel save(StudentModel model);
    StudentModel findById(String id);
    void deleteAll();
    void deleteById(String id);
}
