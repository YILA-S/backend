package backend.services.courses.infra;

import backend.exception.ItemNotFoundException;
import backend.services.courses.domain.Course;

import java.util.Optional;

public class CourseModelAssembler {

    public CourseModel toCourseModel(Course course){
        CourseModel model = new CourseModel();

        model.title = course.getTitle();
        model.course_id = course.getCourse_id();
        model.description = course.getDescription();

        return model;
    }

    public Course toCourse(Optional<CourseModel> courseModel) {
        if(courseModel.isPresent()){
            return new Course(courseModel.get().title, courseModel.get().description, courseModel.get().course_id);
        }
        throw new ItemNotFoundException("Empty course model cannot be converted to course");
    }
}
