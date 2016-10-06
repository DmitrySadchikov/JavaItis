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
        usersDao = new UsersDaoFileBasedImpl("users.txt");
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

}