package backend.services.courses.infra;

import backend.services.courses.domain.Course;
import backend.services.courses.domain.Section;

import java.util.List;
import java.util.stream.Collectors;

public class CourseModelAssembler {

    private SectionModelAssembler sectionModelAssembler = new SectionModelAssembler();

    public CourseModel toCourseModel(Course course){
        CourseModel model = new CourseModel();

        model.title = course.getTitle();
        model.id = course.getCourse_id();
        model.description = course.getDescription();

        model.sections = course.getSectionList().stream()
                .map(section -> sectionModelAssembler.toSectionModel(section))
                .collect(Collectors.toList());

        return model;
    }

    public Course toCourse(CourseModel courseModel) {
        List<Section> sections = courseModel.sections.stream()
                .map(model -> sectionModelAssembler.toSection(model))
                .collect(Collectors.toList());
        return new Course(courseModel.title, courseModel.description, courseModel.id);
    }
}
