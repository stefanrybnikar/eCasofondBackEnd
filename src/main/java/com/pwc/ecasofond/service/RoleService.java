package com.pwc.ecasofond.service;

import com.pwc.ecasofond.model.Role;
import com.pwc.ecasofond.repository.RoleRepository;
import com.pwc.ecasofond.request.body.add.AddRoleBody;
import com.pwc.ecasofond.request.body.update.UpdateRoleBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleService implements Service<Role, AddRoleBody, UpdateRoleBody> {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Iterable<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role get(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public Role add(AddRoleBody role) {
        Role r = new Role();
        r.setName(role.getName());
        return roleRepository.save(r);
    }

    @Override
    public Role update(UpdateRoleBody role) {
        if (roleRepository.findById(role.getId()).isPresent()) {
            Role r = roleRepository.findById(role.getId()).get();
            r.setName(role.getName());
            return roleRepository.save(r);
        }

        return null;
    }

    @Override
    public Boolean delete(Long id) {
        if (roleRepository.findById(id).isPresent()) {
            Role role = roleRepository.findById(id).get();
            roleRepository.delete(role);
            return true;
        }

        return false;
    }
}
