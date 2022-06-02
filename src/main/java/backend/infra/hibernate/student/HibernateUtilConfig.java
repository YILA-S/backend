package backend.infra.hibernate.student;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

@Configuration
public class HibernateUtilConfig {
    private EntityManagerFactory entityManagerFactory;

    public SessionFactory getSessionFactory(){
        if(entityManagerFactory.unwrap(SessionFactory.class) == null){
            throw new NullPointerException("Factory is not hibernate factory");
        }
        return entityManagerFactory.unwrap(SessionFactory.class);
    }
}
