package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private final SessionFactory SESSION_FACTORY;

    @Autowired
    public UserDaoImp(SessionFactory sessionFactory) {
        this.SESSION_FACTORY = sessionFactory;
    }

    private List<User> listUsers;

    @Override
    public void add(User user) {
        try (Session session = SESSION_FACTORY.openSession()) {
            session.save(user);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getListUsers() {
        try (Session session = SESSION_FACTORY.openSession()) {
            listUsers = session.createQuery("from User").getResultList();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return listUsers;
    }

    @Override
    @SuppressWarnings("unchecked")
    public User getUserByCar(String model, Integer series) {
        try (Session session = SESSION_FACTORY.openSession()) {
            listUsers = session.createQuery("from User user " +
                    "where user.car.model = :model and user.car.series = :series").
                    setParameter("model", model).setParameter("series", series).
                    getResultList();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return listUsers.get(0);
    }

    @Override
    public void removeUserById(Long id) {
        try (Session session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();
            session.createQuery("delete User where id = :id").
                    setParameter("id", id).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}