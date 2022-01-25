package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.RoleDao;
import web.model.Role;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceIml implements RoleService {
    RoleDao roleDao;

    @Autowired
    public RoleServiceIml(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Transactional
    @Override
    public Role getRoleById(Long id) {
        return roleDao.getRoleById(id);
    }

    @Transactional
    @Override
    public List<Role> getRolesList() {
        return roleDao.getRolesList();
    }

    @Transactional
    @Override
    public Set<Role> getSetRoles(List<String> roles) {
        Set<Role> setRoles = new HashSet<>();
        for (String id : roles) {
            setRoles.add(getRoleById(Long.parseLong(id)));
        }
        return setRoles;
    }
}
