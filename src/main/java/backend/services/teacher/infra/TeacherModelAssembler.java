package backend.services.teacher.infra;

import backend.services.appuser.domain.AppUser;
import backend.services.student.domain.Student;
import backend.services.teacher.domain.Teacher;

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

    public Teacher toTeacher(TeacherModel found) {
        Teacher teacher = new Teacher(found.id, found.firstName, found.lastName,
                found.birthDate, found.email, found.phone, found.address, found.password);
        teacher.setRoles(found.roles);
        return teacher;
    }
}
