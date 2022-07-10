package backend.services.courses.infra;

import backend.services.student.infra.StudentModel;
import backend.services.teacher.infra.TeacherModel;

import javax.persistence.*;
import java.util.List;

@Entity(name = "sections")
public class SectionModel {

    @Id
    @Column(
            name = "section_id",
            updatable = false
    )
    public String section_id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public CourseModel course;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<StudentModel> studentList;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public TeacherModel teacher;
}
