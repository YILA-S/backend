package backend.services.inscriptions.domain;

import backend.exception.InvalidParameterException;
import backend.services.student.domain.Student;

import java.util.ArrayList;

public class Inscription {
    private final String course;
    private final String section;
    private final String coursePeriod;
    private ArrayList<String> students;
    private String teacher;

    public ArrayList<String> getStudents() {
        return students;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getCourse() {
        return course;
    }

    public String getSection() {
        return section;
    }

    public String getCoursePeriod() {
        return coursePeriod;
    }

    public Inscription(String course, String section, String coursePeriod) {
        this.course = course;
        this.section = section;
        this.coursePeriod = coursePeriod;
        this.students = new ArrayList<>();
    }

    public void setStudents(ArrayList<String> students) {
        this.students = students;
    }

    public void setTeacher(String teacher) throws InvalidParameterException {
        if(this.teacher != null && !this.teacher.equals(""))
            throw new InvalidParameterException("Teacher already exist for this section");
        this.teacher = teacher;
    }

    public void addStudent(Student student) throws InvalidParameterException {
        if(students.stream().anyMatch(s -> s.equals(student.getId())))
            throw new InvalidParameterException(String.format("Student with id: %s already subscribed", student.getId()));
        students.add(student.getId());
    }
}
