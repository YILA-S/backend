package backend.services.coursePeriod.infra;

import backend.services.coursePeriod.domain.Quarter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("CoursePeriod")
public class CoursePeriodModel {
    @Id
    public String id;
    public Quarter quarter;
    public Integer year;
}
