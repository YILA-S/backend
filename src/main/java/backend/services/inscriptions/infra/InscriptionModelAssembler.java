package backend.services.inscriptions.infra;

import backend.services.inscriptions.domain.Inscription;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class InscriptionModelAssembler {
    public InscriptionModel toModel(Inscription inscription) {
        InscriptionIdentification id = new InscriptionIdentification();
        id.courseId = inscription.getCourse().getId();
        id.coursePeriodId = inscription.getCoursePeriod().getId();
        id.sectionId = inscription.getSection().getId();

        InscriptionModel model = new InscriptionModel();
        model.id = id;
        model.students = inscription.getStudents()
                .stream()
                .map(s -> s.getId())
                .collect(Collectors.toCollection(ArrayList::new));
        model.teachers = inscription.getTeachers()
                .stream()
                .map(t -> t.getId())
                .collect(Collectors.toCollection(ArrayList::new));
        return model;
    }
}
