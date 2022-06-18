package backend.services.courses.domain;

public class Course {
    private String id;
    private String title;
    private String description;
    private String code;

    public Course(String id, String title, String description, String code) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.code = code;
    }
}
