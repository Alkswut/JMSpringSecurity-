package web.dao;

import web.model.Role;

import java.util.List;


public interface RoleDao { // extends JpaRepository<User,Long>
    List<Role> getRolesList();

    Role getRoleById(Long id);
}
