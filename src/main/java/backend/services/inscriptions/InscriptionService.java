package backend.services.inscriptions;

import backend.exception.InvalidParameterException;
import backend.exception.ItemNotFoundException;
import backend.services.inscriptions.domain.Inscription;
import backend.services.inscriptions.domain.InscriptionFactory;
import backend.services.inscriptions.infra.InscriptionModelAssembler;
import backend.services.inscriptions.infra.MongoInscriptionRepository;
import backend.services.student.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("inscriptionService")
public class InscriptionService {
    @Autowired
    MongoInscriptionRepository inscriptionRepository;

    InscriptionModelAssembler inscriptionModelAssembler = new InscriptionModelAssembler();

    InscriptionFactory inscriptionFactory = new InscriptionFactory();

    public Inscription createInscription(String courseId, String sectionId, String periodId) throws InvalidParameterException {

        var alreadyExist = inscriptionRepository.
                findByInscriptionCourseAndSectionAndPeriod(courseId, sectionId, periodId) != null;

        if(alreadyExist) throw new InvalidParameterException("Inscriptions already exist for this course");

        Inscription inscription = inscriptionFactory.createInscription(courseId, sectionId, periodId);
        inscriptionRepository.save(inscriptionModelAssembler.toModel(inscription));

        return inscription;
    }

    public Inscription findByInscriptionCourseAndSectionAndPeriod(String courseId, String sectionId, String periodId) throws InvalidParameterException {
        var wantedInscription = inscriptionRepository.
                findByInscriptionCourseAndSectionAndPeriod(courseId, sectionId, periodId);
        return inscriptionModelAssembler.toInscription(wantedInscription);
    }

    public Inscription addStudentToInscription(String courseId, String sectionId, String periodId, Student student) throws InvalidParameterException {

        var wantedInscription = findByInscriptionCourseAndSectionAndPeriod(courseId, sectionId, periodId);
        if(wantedInscription == null) throw new ItemNotFoundException("The section specified doesn't exist");

        wantedInscription.addStudent(student);
        inscriptionRepository.save(inscriptionModelAssembler.toModel(wantedInscription));

        return wantedInscription;
    }

    public Inscription addTeacherToInscription(String courseId, String sectionId, String periodId, String teacherId) throws InvalidParameterException {
        var wantedInscription = findByInscriptionCourseAndSectionAndPeriod(courseId, sectionId, periodId);
        if(wantedInscription == null) throw new ItemNotFoundException("The section specified doesn't exist");

        wantedInscription.setTeacher(teacherId);
        inscriptionRepository.save(inscriptionModelAssembler.toModel(wantedInscription));

        return wantedInscription;
    }
}
