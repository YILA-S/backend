package backend.services.courses.domain;

import backend.services.student.domain.Student;
import backend.services.teacher.domain.Teacher;

import java.util.List;

public class Section {
    private Long sectionId;
    private String location;
    private Course course;
    private Teacher teacher;
    private List<Student> studentList;

    public Section(Long sectionId, String location, Course course) {
        this.sectionId = sectionId;
        this.location = location;
        this.course = course;
    }
}
