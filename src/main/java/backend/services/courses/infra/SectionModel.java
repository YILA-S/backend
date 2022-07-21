package backend.services.courses.infra;

import org.springframework.data.mongodb.core.index.Indexed;

public class SectionModel {
    @Indexed(unique = true)
    public String id;
    public String location;
    public String courseId;
}
