package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    User getUserByName(String name);

//    List<User> allUsers();
//
//    void add(User user);
//
//    void delete(User user);
//
//    void editUser(User user);
//
//    User getById(int id);
}
