package com.pwc.ecasofond.service;

import com.pwc.ecasofond.model.User;
import com.pwc.ecasofond.repository.CompanyRepository;
import com.pwc.ecasofond.repository.ProfessionTypeRepository;
import com.pwc.ecasofond.repository.RoleRepository;
import com.pwc.ecasofond.repository.UserRepository;
import com.pwc.ecasofond.request.body.add.AddUserBody;
import com.pwc.ecasofond.request.body.update.ResetUserPasswordBody;
import com.pwc.ecasofond.request.body.update.UpdateUserBody;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@org.springframework.stereotype.Service
public class UserService implements Service<User, AddUserBody, UpdateUserBody> {
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final RoleRepository roleRepository;
    private final ProfessionTypeRepository professionTypeRepository;
    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;

    public UserService(UserRepository userRepository, CompanyRepository companyRepository, RoleRepository roleRepository, ProfessionTypeRepository professionTypeRepository, InMemoryUserDetailsManager inMemoryUserDetailsManager) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
        this.roleRepository = roleRepository;
        this.professionTypeRepository = professionTypeRepository;
        this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
    }

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
        if (userRepository.existsByEmail(user.getEmail()))
            return null;

        if (userRepository.existsByUsername(user.getUsername()))
            return null;

        if (!companyRepository.existsById(user.getCompanyId()))
            return null;

        if (!roleRepository.existsById(user.getRoleId()))
            return null;

        if (!professionTypeRepository.existsById(user.getProfessionId()))
            return null;

        User u = new User();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        u.setCompanyId(user.getCompanyId());
        u.setRoleId(user.getRoleId());
        u.setProfessionId(user.getProfessionId());
        u.setDisplayName(user.getDisplayName());
        u.setEmail(user.getEmail());
        u.setUsername(user.getUsername());
        u.setPassword(encodedPassword);


        inMemoryUserDetailsManager.createUser(
                org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                        .password("{noop}" + user.getPassword())
                        .authorities(roleRepository.findById(user.getRoleId()).get().getName())
                        .build()
        );

        return userRepository.save(u);
    }

    @Override
    public User update(UpdateUserBody user) {
        if (!userRepository.existsById(user.getId()))
            return null;

        if (!userRepository.existsByEmail(user.getEmail()))
            return null;

        if (!userRepository.existsByUsername(user.getUsername()))
            return null;

        User u = userRepository.findById(user.getId()).get();
        u.setDisplayName(user.getDisplayName());
        u.setEmail(user.getEmail());
        u.setUsername(user.getUsername());
        return userRepository.save(u);
    }

    @Override
    public Boolean delete(Long id) {
        if (!userRepository.existsById(id))
            return false;

        User employee = userRepository.findById(id).get();
        userRepository.delete(employee);
        return true;
    }

    public Boolean resetPassword(ResetUserPasswordBody requestBody) {
        if (!userRepository.existsById(requestBody.getId()))
            return false;

        if (!requestBody.getOldPassword().equals(requestBody.getNewPassword()))
            return false;

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(requestBody.getNewPassword());

        User u = userRepository.findById(requestBody.getId()).get();
        u.setPassword(encodedPassword);
        userRepository.save(u);
        return true;
    }
}
