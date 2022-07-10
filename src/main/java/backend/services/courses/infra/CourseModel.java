package backend.services.courses.infra;

import backend.services.courses.domain.Section;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity(name = "course")
public class CourseModel {
    @Id
    @Column(
            name = "course_id",
            updatable = false
    )
    public String course_id;

    @Column(
            name = "description",
            nullable = false,
            columnDefinition = "TEXT"
    )
    public String description;

    @Column(
            name = "title",
            nullable = false,
            columnDefinition = "TEXT",
            unique = true
    )
    public String title;

    @OneToMany(mappedBy = "course")
    public List<SectionModel> sectionLList;
}
