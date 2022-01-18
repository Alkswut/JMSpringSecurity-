package web.dao;

import web.model.Role;

import java.util.Set;

public interface AdminDao {
    Set<Role> getAllRoles();
    
}
