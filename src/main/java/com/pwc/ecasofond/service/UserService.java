package com.pwc.ecasofond.service;

import com.pwc.ecasofond.model.User;
import com.pwc.ecasofond.repository.CompanyRepository;
import com.pwc.ecasofond.repository.UserRepository;
import com.pwc.ecasofond.request.body.add.AddUserBody;
import com.pwc.ecasofond.request.body.update.UpdateUserBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService implements Service<User, AddUserBody, UpdateUserBody> {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Iterable<User> getAll() {
        Iterable<User> users = userRepository.findAll();
        // strip out the password field
        for (User user : users) {
            user.setPassword(null);
        }

        return users;
    }

    @Override
    public User get(Long id) {
        User user = userRepository.findById(id).orElse(null);
        // strip out the password field
        if (user != null) {
            user.setPassword(null);
        }
        return user;
    }

    @Override
    public User add(AddUserBody user) {
        User u = new User();
        if (companyRepository.findById(user.getCompanyId()).isPresent()) {
            u.setCompanyId(user.getCompanyId());
            u.setRoleId(user.getRoleId());
            u.setProfessionId(user.getProfessionId());
            u.setDisplayName(user.getDisplayName());
            u.setEmail(user.getEmail());
            u.setUsername(user.getUsername());
            u.setPassword(user.getPassword());
            return userRepository.save(u);
        }

        return null;
    }

    @Override
    public User update(UpdateUserBody user) {
        if (userRepository.findById(user.getId()).isPresent()) {
            User u = userRepository.findById(user.getId()).get();
            u.setDisplayName(user.getDisplayName());
            u.setEmail(user.getEmail());
            u.setUsername(user.getUsername());

            if (u.getPassword().equals(user.getOldPassword())) {
                u.setPassword(user.getPassword());
            } else {
                return null;
            }

            return userRepository.save(u);
        }

        return null;
    }

    @Override
    public Boolean delete(Long id) {
        if (userRepository.findById(id).isPresent()) {
            User employee = userRepository.findById(id).get();
            userRepository.delete(employee);
            return true;
        }

        return false;
    }
}
