package backend.services.teacher.infra;

import backend.services.appuser.domain.AppUser;

public class TeacherModelAssembler{
    public TeacherModel createTeacherModel(AppUser newUser) {
        TeacherModel teacherModel = new TeacherModel();
        teacherModel.id = newUser.getId();
        teacherModel.firstName = newUser.getFirstName();
        teacherModel.lastName = newUser.getLastName();
        teacherModel.address = newUser.getAddress();
        teacherModel.birthDate = newUser.getBirthDate();
        teacherModel.email = newUser.getEmail();
        teacherModel.phone = newUser.getPhone();
        teacherModel.roles = newUser.getRoles();
        teacherModel.password = newUser.getPassword();

        return teacherModel;
    }
}
