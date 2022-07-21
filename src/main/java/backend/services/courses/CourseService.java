package backend.services.courses;

import backend.exception.InvalidParameterException;
import backend.exception.ItemNotFoundException;
import backend.services.courses.domain.*;
import backend.services.courses.infra.CourseModel;
import backend.services.courses.infra.CourseModelAssembler;
import backend.services.courses.infra.MongoCourseRepository;
import backend.ui.CourseRequest;
import backend.ui.SectionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("courseService")
public class CourseService {
    @Autowired
    private MongoCourseRepository courseRepository;
    private CourseModelAssembler courseModelAssembler = new CourseModelAssembler();
    private CourseFactory courseFactory = new CourseFactory();

    public Course createCourse(CourseRequest courseRequest) throws InvalidParameterException {
        Course course = courseFactory.createCourse(courseRequest.title, courseRequest.description, courseRequest.code);
        CourseModel courseModel = courseModelAssembler.toCourseModel(course);
        courseRepository.save(courseModel);

        return course;
    }

    public Section createSection(SectionRequest sectionRequest, String courseId) throws InvalidParameterException {
        Course wantedCourse = findCourseById(courseId);
        Section createdSection = courseFactory.createSection(sectionRequest, wantedCourse);

        wantedCourse.sectionAlreadyExist(createdSection);

        wantedCourse.addSection(createdSection);
        courseRepository.save(courseModelAssembler.toCourseModel(wantedCourse));
        return createdSection;
    }

    public Course findCourseById(String code){
        var model = courseRepository.findById(code);
        if(model.isEmpty()) throw new ItemNotFoundException(String.format("Course with id: %s not found", code));
        return courseModelAssembler.toCourse(model.get());
    }

    public void deleteAllCourses() {
        courseRepository.deleteAll();
    }
}
