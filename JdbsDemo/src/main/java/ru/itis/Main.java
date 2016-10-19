package ru.itis;

import ru.itis.factories.ServiceFactory;
import ru.itis.models.User;
import ru.itis.services.UserService;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        UserService userService = ServiceFactory.getInstance().getUserService();

        //userService.addUser(new User("Николай", 30, "qwerty934", "Псков"));
        //userService.deleteUser(15);


        User foundUser = userService.findUserById(5);
        System.out.println("Found user: ");
        System.out.println(foundUser.getName() + " " + foundUser.getAge() + "\n");


        System.out.println("All users: ");
        List<User> users = userService.getAll();
        for(User u : users)
            System.out.println(u.getName() + " " + u.getAge());
    }
}
