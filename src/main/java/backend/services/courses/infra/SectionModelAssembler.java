package backend.services.courses.infra;

import backend.services.courses.domain.Section;

public class SectionModelAssembler {

    public SectionModel toSectionModel(Section section){
        SectionModel model = new SectionModel();

        model.id = section.getId();
        model.location = section.getLocation();
        model.courseId = section.getCourse();

        return model;
    }

    public Section toSection(SectionModel model) {
        return new Section(model.id, model.location, model.courseId);
    }
}
