package backend.services.CoursePeriod;

import backend.exception.InvalidParameterException;
import backend.services.CoursePeriod.domain.CoursePeriod;
import backend.services.CoursePeriod.domain.CoursePeriodFactory;
import backend.services.CoursePeriod.infra.CoursePeriodModelAssembler;
import backend.services.CoursePeriod.infra.MongoCoursePeriodRepository;
import backend.ui.CoursePeriodRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoursePeriodService {

    @Autowired
    private MongoCoursePeriodRepository periodRepository;
    private CoursePeriodModelAssembler periodAssembler = new CoursePeriodModelAssembler();
    private CoursePeriodFactory periodFactory = new CoursePeriodFactory();

    public CoursePeriod createCoursePeriod(CoursePeriodRequest periodRequest) throws InvalidParameterException {
        CoursePeriod period = periodFactory.createCoursePeriod(periodRequest.year, periodRequest.quarter);
        periodRepository.save(periodAssembler.toModel(period));

        return period;
    }

}
