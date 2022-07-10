package backend.services.courses.infra;

import backend.services.courses.domain.Course;

public class CourseModelAssembler {

    public CourseModel toCourseModel(Course course){
        CourseModel model = new CourseModel();

        model.title = course.getTitle();
        model.course_id = course.getCourse_id();
        model.description = course.getDescription();

        return model;
    }

    public Course toCourse(CourseModel courseModel) {
        return new Course(courseModel.title, courseModel.description, courseModel.course_id);
    }
}
