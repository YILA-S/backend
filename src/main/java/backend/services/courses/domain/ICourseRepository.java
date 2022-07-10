package backend.services.courses.domain;

import backend.services.courses.infra.CourseModel;

public interface ICourseRepository{
    void deleteAll();

    CourseModel findById(String code);

    void save(CourseModel courseModel);
}
