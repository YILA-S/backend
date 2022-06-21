package backend.services.courses.domain;


import backend.exception.InvalidParameterException;
import backend.ui.SectionRequest;

public class CourseFactory {

    public Course createCourse(String title, String description, String code) throws InvalidParameterException {

        validateCourseRequest(title, description, code);
        return new Course(title, description, code);
    }

    private void validateCourseRequest(String title, String description, String code) throws InvalidParameterException {
        if(title.equals("") || code.equals("") || description.equals("")) {
            throw new InvalidParameterException("A course should have non empty title, code and description");
        }

        if(description.length() < 20) {
            throw new InvalidParameterException("Course's description should have 20 characters minimum");
        }
    }

    public Section createSection(SectionRequest sectionRequest, Course wantedCourse) throws InvalidParameterException {
        validateSectionRequest(sectionRequest.sectionId, sectionRequest.location);
        return new Section(sectionRequest.sectionId, sectionRequest.location, wantedCourse);
    }

    private void validateSectionRequest(String sectionId, String location) throws InvalidParameterException {
        if(sectionId.length() == 0 || location.length() == 0){
            throw new InvalidParameterException("Section should have a non empty sectionId and location");
        }
    }
}
