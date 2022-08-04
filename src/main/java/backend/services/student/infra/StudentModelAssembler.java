package backend.services.student.infra;

import backend.services.student.domain.Student;
import backend.services.appuser.domain.AppUser;

public class StudentModelAssembler {
    public StudentModel toStudentModel(AppUser user){
        StudentModel studentModel = new StudentModel();

        studentModel.id = user.getId();
        studentModel.firstName = user.getFirstName();
        studentModel.lastName = user.getLastName();
        studentModel.address = user.getAddress();
        studentModel.birthDate = user.getBirthDate();
        studentModel.email = user.getEmail();
        studentModel.phone = user.getPhone();
        studentModel.roles = user.getRoles();
        studentModel.password = user.getPassword();

        return studentModel;
    }

    public Student toStudent(StudentModel studentModel) {
        Student student = new Student(studentModel.id, studentModel.firstName, studentModel.lastName,
                studentModel.birthDate, studentModel.email, studentModel.phone, studentModel.address, studentModel.password);
        student.setRoles(studentModel.roles);
        return student;
    }
}
