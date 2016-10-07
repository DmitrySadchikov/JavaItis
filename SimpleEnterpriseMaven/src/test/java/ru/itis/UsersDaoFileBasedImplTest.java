package ru.itis;

import org.junit.Before;
import org.junit.Test;
import ru.itis.dao.UsersDaoFileBasedImpl;
import ru.itis.models.User;

import java.util.List;

import static org.junit.Assert.*;

public class UsersDaoFileBasedImplTest {

    private UsersDaoFileBasedImpl usersDao;

    @Before
    public void setUp() throws Exception {
        usersDao = new UsersDaoFileBasedImpl();

    }

    @Test
    public void getAll() throws Exception {
        List<User> registeredUsers = usersDao.getAll();
    }


    @Test
    public void getUser() throws Exception {
        User user = usersDao.get(1);
        int i = 0;
    }

    @Test
    public void saveUser() throws Exception {
        User user = new User(5, "Dmitry", "qwerty111", 21);
        usersDao.save(user);
        User user2 = usersDao.get(5);
        int i = 0;
    }

}