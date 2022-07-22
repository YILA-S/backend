package backend.services.courses.domain;

import backend.exception.InvalidParameterException;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String title;
    private String description;
    private String course_id;
    private ArrayList<Section> sectionList;

    public Course(String title, String description, String code) {
        this.title = title;
        this.description = description;
        this.course_id = code;
        this.sectionList = new ArrayList<>();
    }

    public Course(String title, String description, String course_id, ArrayList<Section> sectionList) {
        this.title = title;
        this.description = description;
        this.course_id = course_id;
        this.sectionList = sectionList;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return course_id;
    }

    public List<Section> getSectionList() {
        return sectionList;
    }

    public void setSectionList(ArrayList<Section> sectionList) {
        this.sectionList = sectionList;
    }

    public void addSection(Section section) {
        sectionList.add(section);
    }

    public void sectionAlreadyExist(Section section) throws InvalidParameterException {
        boolean found = sectionList.stream()
                .anyMatch(s -> s.getId().equals(section.getId()));
        if(found)
        throw new InvalidParameterException(String.format("Section with id: %s already exist", section.getId()));
    }
}
