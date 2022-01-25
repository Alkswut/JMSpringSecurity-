package web.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

//    private final Map<String, User> userMap =
//            Collections.singletonMap("admin", new User(1L, "admin", "admin",
//                    Collections.singleton(new Role(1L, "ROLE_ADMIN"))));
//    @Override
//    public User findByUsername(String name) {
//        if (!userMap.containsKey(name)) {
//            return null;
//        }
//        //userDetailsService.loadUserByUsername(name);
//        return userMap.get(name);
//    }

    @Override
    public User findByUsername(String name) {
        Query query = entityManager.createQuery("FROM User where username =?1");
        query.setParameter(1, name);
        return (User) query.getSingleResult();
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
        return session.find(User.class, id);
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

