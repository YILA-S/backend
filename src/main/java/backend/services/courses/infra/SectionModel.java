package backend.services.courses.infra;

import backend.services.student.infra.StudentModel;
import backend.services.teacher.infra.TeacherModel;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;


@Document("section")
public class SectionModel {

    @Id
    public String id;
    @DocumentReference(collection = "course")
    public CourseModel course;
    @DocumentReference(collection = "student", lazy = true)
    public List<StudentModel> studentList;
    @DocumentReference(collection = "teacher")
    public TeacherModel teacher;
}
