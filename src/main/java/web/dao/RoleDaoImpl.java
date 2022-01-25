package web.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext
    EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<Role> getRolesList() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("FROM Role", Role.class).getResultList();
    }

    @Override
    public Role getRoleById(Long id) {
        Session session = entityManager.unwrap(Session.class);
        return session.find(Role.class, id);
    }
}
