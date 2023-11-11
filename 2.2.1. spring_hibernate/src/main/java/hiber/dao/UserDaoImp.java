package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    private TypedQuery<User> listUsersQuery;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getListUsers() {
        listUsersQuery = sessionFactory.getCurrentSession().createQuery("from User");
        return listUsersQuery.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public User getUserByCar(String model, Integer series) {
        listUsersQuery = sessionFactory.getCurrentSession().createQuery("from User user " +
                "where user.car.model = :model and user.car.series = :series").
                setParameter("model", model).setParameter("series", series);
        return listUsersQuery.getResultList().get(0);
    }

    @Override
    public void removeUserById(Long id) {
        sessionFactory.getCurrentSession().createQuery("delete User where id = :id").
                setParameter("id", id).executeUpdate();
    }
}