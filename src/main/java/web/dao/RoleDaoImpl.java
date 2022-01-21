package web.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import web.model.Role;

import javax.persistence.EntityManager;
import java.util.List;

public class RoleDaoImpl implements RoleDao {
    @Autowired
    EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<Role> getRolesList() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("FROM Role").getResultList();
    }

    @Override
    public Role getRoleById(Long id) {
        Session session = entityManager.unwrap(Session.class);
        return session.find(Role.class, id);
    }
}
