package backend.services.CoursePeriod.infra;

import backend.services.CoursePeriod.domain.Quarter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Year;

@Document("CoursePeriod")
public class CoursePeriodModel {
    @Id
    public String id;
    public Quarter quarter;
    public Integer year;
}
