package backend.services.courses.domain;

public class Section {
    private String sectionId;
    private String location;
    private String course;

    public Section(String sectionId, String location, String course) {
        this.sectionId = sectionId;
        this.location = location;
        this.course = course;
    }

    public String getId() {
        return sectionId;
    }

    public String getLocation() {
        return location;
    }

    public String getCourse() {
        return course;
    }
}
