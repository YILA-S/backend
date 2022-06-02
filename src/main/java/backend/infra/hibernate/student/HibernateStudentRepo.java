package backend.infra.hibernate.student;

import backend.domain.student.IStudentRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateStudentRepo implements IStudentRepository {

    private SessionFactory sessionFactory;
    private Session session;

    public void openSession(){
        try{
            session = sessionFactory.openSession();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void save(StudentModel studentModel) {
        openSession();
        try{
            session.beginTransaction();
            session.save(studentModel);
            session.getTransaction().commit();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void find(String studentId) {

    }

    @Override
    public void delete(String studentId) {

    }
}
