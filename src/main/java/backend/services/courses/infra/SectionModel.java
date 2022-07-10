package backend.services.courses.infra;

import backend.services.student.infra.StudentModel;
import backend.services.teacher.infra.TeacherModel;

import java.util.List;

public class SectionModel {

    public String section_id;
    public CourseModel course;
    public List<StudentModel> studentList;
    public TeacherModel teacher;
}
