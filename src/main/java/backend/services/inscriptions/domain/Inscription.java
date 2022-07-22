package backend.services.inscriptions.domain;

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
    private ArrayList<Student> students;
    private ArrayList<Teacher> teachers;

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Teacher> getTeachers() {
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

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public void setTeachers(ArrayList<Teacher> teachers) {
        this.teachers = teachers;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }
}
