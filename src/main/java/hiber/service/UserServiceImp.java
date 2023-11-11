package hiber.service;

import hiber.dao.UserDao;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
        System.out.printf("User %s add\n---------------\n", user.getFirstName());
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getListUsers() {
        System.out.println("List received\n---------------");
        return userDao.getListUsers();
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserByCar(String model, Integer series) {
        System.out.printf("User with Car %s %d:\n", model, series);
        return userDao.getUserByCar(model, series);
    }

    @Transactional
    @Override
    public void removeUserById(Long id) {
        userDao.removeUserById(id);
        System.out.printf("User deleted with ID: %d\n---------------\n", id);
    }
}