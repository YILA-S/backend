package backend.controller;

import backend.exception.InvalidParameterException;
import backend.exception.ItemNotFoundException;
import backend.services.coursePeriod.CoursePeriodService;
import backend.services.courses.CourseService;
import backend.services.inscriptions.InscriptionService;
import backend.services.inscriptions.domain.Inscription;
import backend.services.inscriptions.domain.InscriptionFactory;
import backend.services.inscriptions.infra.InscriptionModelAssembler;
import backend.services.inscriptions.infra.MongoInscriptionRepository;
import backend.services.student.StudentService;
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

    @Resource(name = "studentService")
    StudentService studentService;
    @Resource(name = "inscriptionService")
    InscriptionService inscriptionService;

    @Autowired
    MongoInscriptionRepository inscriptionRepository;

    @PostMapping("/course/{courseId}/sections/{sectionId}/period/{periodId}")
    public Inscription createInscription(@PathVariable("courseId") String courseId,
                                            @PathVariable("sectionId") String sectionId,
                                            @PathVariable("periodId") String periodId) throws InvalidParameterException {
        courseService.findByCourseIdAndBySection(courseId, sectionId);
        periodService.getCoursePeriodById(periodId);

        return inscriptionService.createInscription(courseId, sectionId, periodId);
    }

    @PostMapping("/course/{courseId}/sections/{sectionId}/period/{periodId}/students/{studentId}")
    public Inscription addStudentToInscription(@PathVariable("courseId") String courseId,
                                               @PathVariable("sectionId") String sectionId,
                                               @PathVariable("periodId") String periodId,
                                               @PathVariable("studentId") String studentId) throws ItemNotFoundException, InvalidParameterException {
        courseService.findByCourseIdAndBySection(courseId, sectionId);
        periodService.getCoursePeriodById(periodId);
        var student = studentService.findById(studentId);

        return inscriptionService.addStudentToInscription(courseId, sectionId, periodId, student);
    }
}
