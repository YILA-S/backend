package backend.services.courses.domain;

import java.util.List;

public class Course {
    private String title;
    private String description;
    private String course_id;
    private List<Section> sectionList;

    public Course(String title, String description, String code) {
        this.title = title;
        this.description = description;
        this.course_id = code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setSectionList(List<Section> sectionList) {
        this.sectionList = sectionList;
    }

    public void addSection(Section section) {
        sectionList.add(section);
    }
}
