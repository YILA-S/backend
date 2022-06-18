package backend.services.courses.infra;

import javax.persistence.Column;
import javax.persistence.Id;

public class CourseModel {
    @Id
    @Column(
            name = "id",
            updatable = false
    )
    public String id;
}
