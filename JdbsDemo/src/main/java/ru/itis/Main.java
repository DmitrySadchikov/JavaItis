package ru.itis;

import ru.itis.dao.UsersDao;
import ru.itis.factories.DaoFactory;
import ru.itis.models.User;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        UsersDao usersDao = DaoFactory.getInstance().getUsersDao();
        //CarsDao carsDao = DaoFactory.getInstance().getCarsDao();
        //usersDao.add(new User(30, "Николай", 30, "qwerty934", "Псков"));
        List<User> users = usersDao.getAll();
        for(User u : users)
            System.out.println(u.getName() + " " + u.getAge());
    }
}
