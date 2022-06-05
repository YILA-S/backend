package backend.services.teacher.infra;

import backend.services.user.infra.UserModel;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "teacher")
public class TeacherModel extends UserModel {
}
