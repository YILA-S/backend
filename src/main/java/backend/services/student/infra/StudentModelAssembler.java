package backend.services.student.infra;

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
}
