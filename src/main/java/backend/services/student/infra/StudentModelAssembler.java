package backend.services.student.infra;

import backend.services.student.domain.Student;
import backend.services.user.domain.User;

public class StudentModelAssembler {
    public StudentModel toStudentModel(User user){
        StudentModel studentModel = new StudentModel();

        studentModel.id = user.getId();
        studentModel.firstName = user.getFirstName();
        studentModel.lastName = user.getLastName();
        studentModel.address = user.getAddress();
        studentModel.birthDate = user.getBirthDate();
        studentModel.email = user.getEmail();
        studentModel.phone = user.getPhone();

        return studentModel;
    }

    public Student toStudent(StudentModel studentModel) {
        Student student = new Student(studentModel.id, studentModel.firstName, studentModel.lastName,
                studentModel.birthDate, studentModel.email, studentModel.phone, studentModel.address);
        return student;
    }
}
