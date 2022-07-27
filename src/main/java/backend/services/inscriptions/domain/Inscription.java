package backend.services.inscriptions.domain;

import backend.exception.InvalidParameterException;
import backend.services.coursePeriod.domain.CoursePeriod;
import backend.services.courses.domain.Course;
import backend.services.courses.domain.Section;
import backend.services.student.domain.Student;
import backend.services.teacher.domain.Teacher;

import java.util.ArrayList;

public class Inscription {
    private String course;
    private String section;
    private String coursePeriod;
    private ArrayList<String> students;
    private ArrayList<String> teachers;

    public ArrayList<String> getStudents() {
        return students;
    }

    public ArrayList<String> getTeachers() {
        return teachers;
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
        this.teachers = new ArrayList<>();
    }

    public void setStudents(ArrayList<String> students) {
        this.students = students;
    }

    public void setTeachers(ArrayList<String> teachers) {
        this.teachers = teachers;
    }

    public void addStudent(Student student) throws InvalidParameterException {
        if(students.stream().anyMatch(s -> s.equals(student.getId())))
            throw new InvalidParameterException(String.format("Student with id: %s already subscribed", student.getId()));
        students.add(student.getId());
    }

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher.getId());
    }
}
