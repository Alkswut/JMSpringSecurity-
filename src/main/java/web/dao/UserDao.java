package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import java.util.List;

@Repository
public interface UserDao {//extends JpaRepository<User,Long>

    User findByUsername(String name);

    void addUser(User user);

    User getUserById(Long id);

    List<User> listUsers();

    void deleteUser(User user);

    void editUser(User user);
}