package backend.infra.hibernate.student;

import backend.domain.student.Student;

public class StudentModelAssembler {

    public StudentModel createStudentModel(Student student){
        StudentModel studentModel = new StudentModel();

        studentModel.id = student.getId();
        studentModel.firstName = student.getFirstName();
        studentModel.lastName = student.getLastName();
        studentModel.address = student.getAddress();
        studentModel.birthDate = student.getBirthDate();
        studentModel.email = student.getEmail();
        studentModel.phone = student.getPhone();

        return studentModel;
    }
}