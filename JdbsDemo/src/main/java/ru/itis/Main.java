package ru.itis;

import ru.itis.dao.CarsDao;
import ru.itis.dao.UsersDao;
import ru.itis.dao.UsersDaoJdbcImpl;
import ru.itis.models.User;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        UsersDao usersDao = new UsersDaoJdbcImpl();
        //CarsDao carsDao = DaoSupportFactory.getInstance().getCarsDao();
        List<User> users = usersDao.getAll();
        for(User u : users)
            System.out.println(u.getName() + " " + u.getAge());
    }
}
