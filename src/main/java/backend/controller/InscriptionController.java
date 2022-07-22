package backend.controller;

import backend.exception.InvalidParameterException;
import backend.services.coursePeriod.CoursePeriodService;
import backend.services.courses.CourseService;
import backend.services.inscriptions.domain.Inscription;
import backend.services.inscriptions.domain.InscriptionFactory;
import backend.services.inscriptions.infra.InscriptionModelAssembler;
import backend.services.inscriptions.infra.MongoInscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class InscriptionController {

    @Resource(name = "courseService")
    CourseService courseService;
    @Resource(name = "coursePeriodService")
    CoursePeriodService periodService;

    @Autowired
    MongoInscriptionRepository inscriptionRepository;

    InscriptionModelAssembler inscriptionModelAssembler = new InscriptionModelAssembler();

    InscriptionFactory inscriptionFactory = new InscriptionFactory();
    @PostMapping("/course/{courseId}/sections/{sectionId}/period/{periodId}")
    public Inscription createInscription(@PathVariable("courseId") String courseId,
                                            @PathVariable("sectionId") String sectionId,
                                            @PathVariable("periodId") String periodId) throws InvalidParameterException {
        courseService.findByCourseIdAndBySection(courseId, sectionId);
        periodService.getCoursePeriodById(periodId);

        var alreadyExist = inscriptionRepository.
                findByInscriptionCourseAndSectionAndPeriod(courseId, sectionId, periodId) != null;

        if(alreadyExist) throw new InvalidParameterException("Inscriptions already exist for this course");

        Inscription inscription = inscriptionFactory.createInscription(courseId, sectionId, periodId);
        inscriptionRepository.save(inscriptionModelAssembler.toModel(inscription));

        return inscription;
    }
}
