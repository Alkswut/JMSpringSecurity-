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
public class UserDaoImpl implements UserDao{
    @PersistenceContext
    private EntityManager entityManager;

    private final Map<String, User> userMap =
            Collections.singletonMap("ADMIN", new User(1L, "ADMIN", "ADMIN",
                    Collections.singleton(new Role(1L, "ROLE_ADMIN"))));

//    @Override
//    public User findByUsername(String name) {
//        if (!userMap.containsKey(name)) {
//            return null;
//        }
//        return userMap.get(name);
//    }

    @Override
    public User findByUsername(String name) {
        Session session = entityManager.unwrap(Session.class);
        if (session.get(User.class, name) == null) {
            return null;
        }
        return session.get(User.class, name);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> listUsers() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public void addUser(User user) {
        Session session = entityManager.unwrap(Session.class);
        session.persist(user);
    }


    @Override
    public User getUserById(Long id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(User.class, id);
    }

    @Override
    public void deleteUser(User user) {
        User merge = entityManager.merge(user);
        entityManager.remove(merge);
    }

    @Override
    public void editUser(User user) {
        Session session = entityManager.unwrap(Session.class);
        session.update(user);
    }
}

