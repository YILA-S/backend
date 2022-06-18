package backend.services.teacher.infra;

import backend.services.user.domain.User;

public class TeacherModelAssembler{
    public TeacherModel createTeacherModel(User newUser) {
        TeacherModel teacherModel = new TeacherModel();
        teacherModel.id = newUser.getId();
        teacherModel.firstName = newUser.getFirstName();
        teacherModel.lastName = newUser.getLastName();
        teacherModel.address = newUser.getAddress();
        teacherModel.birthDate = newUser.getBirthDate();
        teacherModel.email = newUser.getEmail();
        teacherModel.phone = newUser.getPhone();

        return teacherModel;
    }
}
