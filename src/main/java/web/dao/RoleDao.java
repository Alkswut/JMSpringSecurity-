package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;

import java.util.List;

@Repository
public interface RoleDao { // extends JpaRepository<User,Long>
    List<Role> getRolesList();

    Role getRoleById(Long id);
}
