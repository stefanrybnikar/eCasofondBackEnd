package com.pwc.ecasofond.service;

import com.pwc.ecasofond.model.User;
import com.pwc.ecasofond.repository.CompanyRepository;
import com.pwc.ecasofond.repository.ProfessionTypeRepository;
import com.pwc.ecasofond.repository.RoleRepository;
import com.pwc.ecasofond.repository.UserRepository;
import com.pwc.ecasofond.request.body.add.AddUserBody;
import com.pwc.ecasofond.request.body.update.ResetUserPasswordBody;
import com.pwc.ecasofond.request.body.update.UpdateUserBody;
import com.pwc.ecasofond.request.response.UserResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;

@org.springframework.stereotype.Service
public class UserService implements Service<UserResponse, AddUserBody, UpdateUserBody, User> {
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
    public UserResponse convertToResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setDisplayName(user.getDisplayName());
        userResponse.setEmail(user.getEmail());
        userResponse.setUsername(user.getUsername());
        userResponse.setCompanyId(user.getCompanyId());
        userResponse.setProfessionTypeId(user.getProfessionTypeId());
        userResponse.setRoleId(user.getRoleId());

        return userResponse;
    }

    @Override
    public Iterable<UserResponse> getAll() {
        Iterable<User> users = userRepository.findAll();

        ArrayList<UserResponse> response = new ArrayList<>();

        for (User user : users) {
            response.add(convertToResponse(user));
        }

        return response;
    }

    @Override
    public UserResponse get(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null)
            return null;

        return convertToResponse(user);
    }

    @Override
    public UserResponse add(AddUserBody user) {
        if (userRepository.existsByEmail(user.getEmail()))
            return null;

        if (userRepository.existsByUsername(user.getUsername()))
            return null;

        if (!companyRepository.existsById(user.getCompanyId()))
            return null;

        if (!roleRepository.existsById(user.getRoleId()))
            return null;

        if (!professionTypeRepository.existsById(user.getProfessionTypeId()))
            return null;

        User u = new User();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        u.setCompanyId(user.getCompanyId());
        u.setRoleId(user.getRoleId());
        u.setProfessionTypeId(user.getProfessionTypeId());
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

        return convertToResponse(userRepository.save(u));
    }

    @Override
    public UserResponse update(UpdateUserBody user) {
        if (!userRepository.existsById(user.getId()))
            return null;

        if (userRepository.existsByEmail(user.getEmail()))
            return null;

        if (userRepository.existsByUsername(user.getUsername()))
            return null;

        User u = userRepository.findById(user.getId()).get();
        u.setDisplayName(user.getDisplayName());
        u.setEmail(user.getEmail());
        u.setUsername(user.getUsername());
        return convertToResponse(userRepository.save(u));
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
