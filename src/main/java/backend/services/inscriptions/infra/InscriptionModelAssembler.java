package backend.services.inscriptions.infra;

import backend.exception.InvalidParameterException;
import backend.services.inscriptions.domain.Inscription;

public class InscriptionModelAssembler {
    public InscriptionModel toModel(Inscription inscription) {
        InscriptionIdentification id = new InscriptionIdentification();
        id.courseId = inscription.getCourse();
        id.coursePeriodId = inscription.getCoursePeriod();
        id.sectionId = inscription.getSection();

        InscriptionModel model = new InscriptionModel();
        model.id = id;
        model.students = inscription.getStudents();
        model.teacher = inscription.getTeacher();
        return model;
    }

    public Inscription toInscription(InscriptionModel model) throws InvalidParameterException {
        Inscription inscription = new Inscription(model.id.courseId, model.id.sectionId, model.id.coursePeriodId);
        inscription.setStudents(model.students);
        inscription.setTeacher(model.teacher);

        return inscription;
    }
}
