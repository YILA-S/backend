package backend.services.student.infra;

import backend.services.user.infra.UserModel;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class StudentModel extends UserModel {
}
