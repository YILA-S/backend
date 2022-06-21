package backend.services.courses;

import backend.exception.InvalidParameterException;
import backend.services.courses.domain.*;
import backend.services.courses.infra.CourseModel;
import backend.services.courses.infra.CourseModelAssembler;
import backend.ui.CourseRequest;
import backend.ui.SectionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("courseService")
public class CourseService {

    @Autowired
    private ICourseRepository courseRepository;

    @Autowired
    private ISectionRepository sectionRepository;
    private CourseModelAssembler courseModelAssembler = new CourseModelAssembler();
    private CourseFactory courseFactory = new CourseFactory();

    public Course createCourse(CourseRequest courseRequest) throws InvalidParameterException {
        Course course = courseFactory.createCourse(courseRequest.title, courseRequest.description, courseRequest.code);
        CourseModel courseModel = courseModelAssembler.toCourseModel(course);
        courseRepository.save(courseModel);

        return course;
    }

    public Section createSection(SectionRequest sectionRequest) throws InvalidParameterException {
        Course wantedCourse = findCourseById(sectionRequest.courseId);
        return courseFactory.createSection(sectionRequest, wantedCourse);
    }

    public Course findCourseById(String code){
        var courseModel = courseRepository.findById(code);

        return courseModelAssembler.toCourse(courseModel);
    }

    public void deleteAllCourses() {
        courseRepository.deleteAll();
    }
}