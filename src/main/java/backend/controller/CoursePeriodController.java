package backend.controller;

import backend.exception.InvalidParameterException;
import backend.services.CoursePeriod.CoursePeriodService;
import backend.services.CoursePeriod.domain.CoursePeriod;
import backend.ui.CoursePeriodRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class CoursePeriodController {

    @Resource(name = "coursePeriodService")
    private CoursePeriodService periodService;

    @PostMapping("/coursePeriod")
    public ResponseEntity createCoursePeriod(@RequestBody CoursePeriodRequest periodRequest) throws InvalidParameterException {
        validatePeriodRequest(periodRequest);
        return new ResponseEntity<CoursePeriod>(periodService.createCoursePeriod(periodRequest), HttpStatus.CREATED);
    }

    private void validatePeriodRequest(CoursePeriodRequest periodRequest) throws InvalidParameterException {
        if(periodRequest.quarter == null || periodRequest.year == null)
            throw new InvalidParameterException("Period Request should contain a quarter and an associate year");
    }
}
