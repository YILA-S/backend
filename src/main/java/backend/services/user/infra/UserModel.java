package backend.services.user.infra;

import javax.persistence.*;

@Entity(name = "user")
public class UserModel {

    @Id
    @Column(name = "id", updatable = false)
    public String id;
}
