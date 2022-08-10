package backend.services.coursePeriod;

import backend.exception.InvalidParameterException;
import backend.exception.ItemNotFoundException;
import backend.services.coursePeriod.domain.CoursePeriod;
import backend.services.coursePeriod.domain.CoursePeriodFactory;
import backend.services.coursePeriod.infra.CoursePeriodModelAssembler;
import backend.services.coursePeriod.infra.MongoCoursePeriodRepository;
import backend.ui.CoursePeriodRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoursePeriodService {

    @Autowired
    private MongoCoursePeriodRepository periodRepository;
    private final CoursePeriodModelAssembler periodAssembler = new CoursePeriodModelAssembler();
    private final CoursePeriodFactory periodFactory = new CoursePeriodFactory();

    public CoursePeriod createCoursePeriod(CoursePeriodRequest periodRequest) throws InvalidParameterException {
        CoursePeriod period = periodFactory.createCoursePeriod(periodRequest.year, periodRequest.quarter);
        periodRepository.save(periodAssembler.toModel(period));

        return period;
    }

    public CoursePeriod getCoursePeriodById(String periodId) {
        var model = periodRepository.findById(periodId);

        if(model.isEmpty())
            throw new ItemNotFoundException(String.format("CoursePeriod with id: %s not found", periodId));

        return periodAssembler.toCoursePeriod(model.get());
    }

}
