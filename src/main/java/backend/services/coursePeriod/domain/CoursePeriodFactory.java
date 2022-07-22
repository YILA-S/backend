package backend.services.coursePeriod.domain;

import backend.exception.InvalidParameterException;

import java.time.Year;

public class CoursePeriodFactory {

    public CoursePeriod createCoursePeriod(Integer year, String quarter) throws InvalidParameterException {
        validateYear(year);
        validateQuarter(quarter);
        return new CoursePeriod(Quarter.valueOf(quarter), Year.of(year));
    }

    private void validateQuarter(String quarter) throws InvalidParameterException {
        try {
            Quarter.valueOf(quarter);
        }
        catch (Exception e){
            throw new InvalidParameterException("The specified Quarter specified is invalid");
        }
    }

    private void validateYear(Integer year) throws InvalidParameterException {
        if(Year.of(year).compareTo(Year.now()) == -1)
            throw new InvalidParameterException("Cannot create a CoursePeriod with a year in the past");
    }
}
