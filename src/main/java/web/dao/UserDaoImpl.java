package web.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    // name - уникальное значение, выступает в качестве ключа Map
    private final Map<String, User> userMap = Collections.singletonMap("test",
            new User(1L, "test", "test", Collections.singleton(new Role(1L, "ROLE_USER"))));

    @Override
    public User getUserByName(String name) {
        if (!userMap.containsKey(name)) {
            return null;
        }
        return userMap.get(name);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> allUsers() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public void add(User user) {
        Session session = entityManager.unwrap(Session.class);
        session.persist(user);
    }

    @Override
    public void delete(User user) {
        User merge = entityManager.merge(user);
        entityManager.remove(merge);
    }

    @Override
    public void editUser(User user) {
        Session session = entityManager.unwrap(Session.class);
        session.update(user);
    }

    @Override
    public User getById(int id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(User.class, id);
    }
}

