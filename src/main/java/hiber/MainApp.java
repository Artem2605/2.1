package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);
        User firstUser = new User("Artem", "Alav", "aaa@mail.ru");
        Car firstCar = new Car("MERS", 221);
        firstUser.setCar(firstCar);
        userService.add(firstUser);
        User secondUser = new User("Ivan", "Romanov", "sss@mail.ru");
        Car secondCar = new Car("BMW", 535);
        secondUser.setCar(secondCar);
        userService.add(secondUser);
        User thirdUser = new User("Vasiliy", "Petrov", "qqq@mail.ru");
        Car thirdCar = new Car("Toyota", 350);
        thirdUser.setCar(thirdCar);
        userService.add(thirdUser);
        userService.removeUserById(Long.valueOf("2"));
        List<User> users = userService.getListUsers();
        for (User temp : users) {
            System.out.println(temp);
        }
        System.out.println(userService.getUserByCar("MERS", 221).toString());
        context.close();
    }
}