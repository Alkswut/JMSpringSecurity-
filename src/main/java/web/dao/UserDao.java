package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao { //extends JpaRepository<User,Long>
    User findByUsername(String name);

    void addUser(User user);

    User getUserById(Long id);

    List<User> listUsers();

    void deleteUser(User user);

    void editUser(User user);
}