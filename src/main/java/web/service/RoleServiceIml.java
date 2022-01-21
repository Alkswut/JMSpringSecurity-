package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import web.dao.RoleDao;
import web.model.Role;

import java.util.List;

public class RoleServiceIml implements RoleService {
    RoleDao roleDao;

    @Autowired
    public RoleServiceIml(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role getRoleById(Long id) {
        return roleDao.getRoleById(id);
    }

    @Override
    public List<Role> getRolesList() {
        return roleDao.getRolesList();
    }
}
