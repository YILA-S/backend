package backend.services.courses.domain;

public class Section {
    private final String sectionId;
    private final String location;
    private final String course;

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
