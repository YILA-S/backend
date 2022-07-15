package backend.services.courses.infra;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;


import java.util.List;

@Document("course")
public class CourseModel {
    @Id
    public String id;
    public String description;
    public String title;
    @DocumentReference(collection = "section")
    public List<SectionModel> sections;
}
