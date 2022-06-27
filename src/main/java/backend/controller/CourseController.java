package backend.controller;

import backend.exception.InvalidParameterException;
import backend.exception.ItemNotFoundException;
import backend.services.courses.CourseService;
import backend.services.courses.domain.Course;
import backend.services.courses.domain.Section;
import backend.ui.CourseRequest;
import backend.ui.SectionRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class CourseController {

    @Resource(name = "courseService")
    private CourseService courseService;

    @PostMapping("/course")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Course createCourse(@RequestBody CourseRequest courseRequest) throws InvalidParameterException {
        validateCourseRequest(courseRequest);
        return courseService.createCourse(courseRequest);
    }

    @GetMapping("/course/{id}")
    @ResponseStatus(code = HttpStatus.FOUND)
    public Course findByCourseId(@PathVariable("id") String courseId){
        var course = courseService.findCourseById(courseId);
        if(course == null) {
            throw new ItemNotFoundException(String.format("Course with Id : %s not found", courseId));
        }
        return course;
    }

    @DeleteMapping("/course")
    @ResponseStatus(code = HttpStatus.OK)
    public String deleteAllCourse(){
        courseService.deleteAllCourses();
        return "All courses deleted";
    }

    private void validateCourseRequest(CourseRequest courseRequest) throws InvalidParameterException {
        if(courseRequest.description == null || courseRequest.title == null || courseRequest.code == null) {
            throw new InvalidParameterException("A course should have a code, a description and a title");
        }
    }

    @PostMapping("/course/{id}/section/")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Section createSection(@RequestBody SectionRequest sectionRequest) {
        return null;
    }
}
