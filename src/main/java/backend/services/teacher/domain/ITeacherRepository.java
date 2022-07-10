package backend.services.teacher.domain;

import backend.services.teacher.infra.TeacherModel;

public interface ITeacherRepository {
    TeacherModel save(TeacherModel model);
    TeacherModel findById(String id);
    void deleteAll();
    void deleteById(String id);
}
