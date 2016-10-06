package ru.itis.dao;

import com.google.common.base.Splitter;
import ru.itis.models.User;

import javax.jws.soap.SOAPBinding;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDaoFileBasedImpl implements UsersDao {

    private String filename;
    private RandomAccessFile fileReader;
    // private BufferedReader fileReader;

    public UsersDaoFileBasedImpl(String fileName) {
        try {
            this.filename = fileName;
            fileReader = new RandomAccessFile(new File(this.filename), "rw");
            //fileReader = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        try {
            fileReader = new RandomAccessFile(new File(this.filename), "rw");
            String currentLine = fileReader.readLine();
            while (currentLine != null) {
                User currentUser = parseStringAsUser(currentLine);
                result.add(currentUser);
                currentLine = fileReader.readLine();
            }
            //fileReader.seek(0);
        } catch (IOException e) {
            System.out.println("SomeError");
        }

        return result;
    }

    @Override
    public User get (int userId)  {
        List<User> users = this.getAll();
        for(User user : users) {
            if(user.getId() == userId)
                return user;
        }
        new Exception("User not found!");
        return null;
    }

    @Override
    public void save(User user) {
        try {
            fileReader.seek(fileReader.length());
            fileReader.writeBytes("\n" + user.getId() + " " + user.getName()
                    + " " + user.getPassword() + " " + user.getAge());
            fileReader.seek(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int userId) {
        List<User> users = this.getAll();
        boolean f = false;
        for(User user : users)
            if(user.getId() == userId) {
                users.remove(userId);
                f = true;
                break;
            }
        if(!f)
            System.out.println("Error! This id does not exist!");
        File file = new File(filename);
        file.delete();
        try {
            file.createNewFile();
            fileReader = new RandomAccessFile(file, "rw");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            boolean b = true;
            for(User user : users) {
                if(b) {
                    fileReader.writeBytes(user.getId() + " " + user.getName()
                            + " " + user.getPassword() + " " + user.getAge());
                    b = false;
                }
                else
                    fileReader.writeBytes("\n" + user.getId() + " " + user.getName()
                            + " " + user.getPassword() + " " + user.getAge());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private User parseStringAsUser(String line) {
        Splitter splitter = Splitter.on(" ");

        List<String> lines = splitter.splitToList(line);

        int id = Integer.parseInt(lines.get(0));
        String name = lines.get(1);
        String password = lines.get(2);
        int age = Integer.parseInt(lines.get(3));

        return new User(id, name, password, age);
    }
}
