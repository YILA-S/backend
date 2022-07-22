package backend.services.coursePeriod.infra;

import backend.services.coursePeriod.domain.CoursePeriod;

import java.time.Year;

public class CoursePeriodModelAssembler {

    public CoursePeriodModel toModel(CoursePeriod period){

        CoursePeriodModel model = new CoursePeriodModel();
        model.quarter = period.getQuarter();
        model.year = period.getYear().getValue();
        model.id = period.getId();

        return model;
    }

    public CoursePeriod toCoursePeriod(CoursePeriodModel model){
        return new CoursePeriod(model.quarter, Year.of(model.year), model.id);
    }
}
