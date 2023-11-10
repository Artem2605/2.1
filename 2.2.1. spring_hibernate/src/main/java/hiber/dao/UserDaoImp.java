package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    //todo - переделать в одномодульный maven-project
    //todo: используем конструктив try_cach_with_resources для Session
    //todo: listUsersQuery нейминг переменных.. вместо например query
    //todo jdk11
    //todo .gitignore - если нет, испорчу репозиторий

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public User getUser(Car car) {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User user " +
                "where user.car.model = :model and user.car.series = :series").
                setParameter("model", car.getModel()).setParameter("series", car.getSeries());
        return query.getResultList().get(0);
    }
}