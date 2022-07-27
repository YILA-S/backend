package backend.services.inscriptions.infra;

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
        model.teachers = inscription.getTeachers();
        return model;
    }

    public Inscription toInscription(InscriptionModel model) {
        Inscription inscription = new Inscription(model.id.courseId, model.id.sectionId, model.id.coursePeriodId);
        inscription.setStudents(model.students);
        inscription.setTeachers(model.teachers);

        return inscription;
    }
}
