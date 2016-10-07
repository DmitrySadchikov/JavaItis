package ru.itis;

import javafx.collections.transformation.SortedList;
import ru.itis.dao.UsersDao;
import ru.itis.dao.UsersDaoFileBasedImpl;
import ru.itis.models.User;
import ru.itis.service.SimpleUsersService;
import ru.itis.service.SimpleUsersServiceImpl;

import java.lang.reflect.Constructor;

public class Main {

    public static void main(String[] args) {
        //UsersDao usersDao = new UsersDaoFileBasedImpl("users.txt");
        UsersDao usersDao = DaoSupportFactory.getInstance().getUsersDao();
        usersDao.save(new User(3, "Dmitry", "qwerty010", 21));
        System.out.println(usersDao.get(3));

        usersDao.delete(2);

        //SimpleUsersService service = new SimpleUsersServiceImpl(usersDao);
        SimpleUsersService service = DaoSupportFactory.getInstance().getService();
        service.setUsersDao(usersDao);
        if(service.isRegistered("Marsel", "qwerty007"))
            System.out.println("A user is registered");
        else
            System.out.println("A user is not registered");
    }
}
