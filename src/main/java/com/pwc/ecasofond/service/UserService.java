package com.pwc.ecasofond.service;

import com.pwc.ecasofond.model.User;
import com.pwc.ecasofond.repository.CompanyRepository;
import com.pwc.ecasofond.repository.ProfessionTypeRepository;
import com.pwc.ecasofond.repository.RoleRepository;
import com.pwc.ecasofond.repository.UserRepository;
import com.pwc.ecasofond.request.body.add.AddUserBody;
import com.pwc.ecasofond.request.body.update.ResetUserPasswordBody;
import com.pwc.ecasofond.request.body.update.UpdateUserBody;
import com.pwc.ecasofond.request.response.ApiResponse;
import com.pwc.ecasofond.request.response.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
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
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, CompanyRepository companyRepository, RoleRepository roleRepository, ProfessionTypeRepository professionTypeRepository, InMemoryUserDetailsManager inMemoryUserDetailsManager, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
        this.roleRepository = roleRepository;
        this.professionTypeRepository = professionTypeRepository;
        this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
        this.passwordEncoder = passwordEncoder;
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
    public ApiResponse<Iterable<UserResponse>> getAll() {
        ApiResponse<Iterable<UserResponse>> response = new ApiResponse<>();

        Iterable<User> users = userRepository.findAll();

        ArrayList<UserResponse> userResponses = new ArrayList<>();

        for (User user : users) {
            userResponses.add(convertToResponse(user));
        }

        response.setData(userResponses);
        response.setStatus(HttpStatus.OK);
        response.setMessage("Found " + userResponses.size() + " users");

        return response;
    }

    @Override
    public ApiResponse<UserResponse> get(Long id) {
        ApiResponse<UserResponse> response = new ApiResponse<>();

        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("User not found");
            return response;
        }

        response.setStatus(HttpStatus.OK);
        response.setMessage("User found");
        response.setData(convertToResponse(user));

        return response;
    }

    public ApiResponse<UserResponse> getByUsername(String username) {
        ApiResponse<UserResponse> response = new ApiResponse<>();

        User user = userRepository.findByUsername(username);
        if (user == null) {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("User not found");
            return response;
        }

        response.setStatus(HttpStatus.OK);
        response.setMessage("User found");
        response.setData(convertToResponse(user));

        return response;
    }

    @Override
    public ApiResponse<UserResponse> add(AddUserBody user) {
        ApiResponse<UserResponse> response = new ApiResponse<>();

        if (userRepository.existsByEmail(user.getEmail())) {
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage("Email already exists");
            return response;
        }

        if (userRepository.existsByUsername(user.getUsername())) {
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage("Username already exists");
            return response;
        }

        if (user.getCompanyId() != null)
            if (!companyRepository.existsById(user.getCompanyId())) {
                response.setStatus(HttpStatus.BAD_REQUEST);
                response.setMessage("Company not found");
                return response;
            }

        if (!roleRepository.existsById(user.getRoleId())) {
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage("Role not found");
            return response;
        }

        if (user.getProfessionTypeId() != null)
            if (!professionTypeRepository.existsById(user.getProfessionTypeId())) {
                response.setStatus(HttpStatus.BAD_REQUEST);
                response.setMessage("Profession type not found");
                return response;
            }

        User u = new User();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        u.setCompanyId(user.getCompanyId());
        u.setRoleId(user.getRoleId());
        u.setProfessionTypeId(user.getProfessionTypeId());
        u.setDisplayName(user.getDisplayName());
        u.setEmail(user.getEmail());
        u.setUsername(user.getUsername());
        u.setPassword(encodedPassword);


        inMemoryUserDetailsManager.createUser(
                org.springframework.security.core.userdetails.User
                        .withUsername(user.getUsername())
                        .password(encodedPassword)
                        .authorities(roleRepository.findById(user.getRoleId()).get().getName())
                        .build()
        );

        response.setStatus(HttpStatus.OK);
        response.setMessage("User created");
        response.setData(convertToResponse(userRepository.save(u)));

        return response;
    }

    @Override
    public ApiResponse<UserResponse> update(UpdateUserBody user) {
        ApiResponse<UserResponse> response = new ApiResponse<>();

        if (!userRepository.existsById(user.getId())) {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("User not found");
            return response;
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage("Email already exists");
            return response;
        }

        if (userRepository.existsByUsername(user.getUsername())) {
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage("Username already exists");
            return response;
        }

        User u = userRepository.findById(user.getId()).get();
        u.setDisplayName(user.getDisplayName());
        u.setEmail(user.getEmail());
        u.setUsername(user.getUsername());

        response.setStatus(HttpStatus.OK);
        response.setMessage("User updated");
        response.setData(convertToResponse(userRepository.save(u)));

        return response;
    }

    @Override
    public ApiResponse<Boolean> delete(Long id) {
        ApiResponse<Boolean> response = new ApiResponse<>();

        if (!userRepository.existsById(id)) {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("User not found");
            return response;
        }

        User employee = userRepository.findById(id).get();
        userRepository.delete(employee);

        inMemoryUserDetailsManager.deleteUser(employee.getUsername());

        response.setStatus(HttpStatus.OK);
        response.setMessage("User deleted");
        response.setData(true);

        return response;
    }

    public ApiResponse<Boolean> resetPassword(ResetUserPasswordBody requestBody) {
        ApiResponse<Boolean> response = new ApiResponse<>();

        if (!userRepository.existsById(requestBody.getId())) {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("User not found");
            return response;
        }

        if (!requestBody.getOldPassword().equals(requestBody.getNewPassword())) {
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage("Passwords do not match");
            return response;
        }

        String encodedPassword = passwordEncoder.encode(requestBody.getNewPassword());

        User u = userRepository.findById(requestBody.getId()).get();
        u.setPassword(encodedPassword);
        userRepository.save(u);

        UserDetails userDetails = inMemoryUserDetailsManager.loadUserByUsername(u.getUsername());
        inMemoryUserDetailsManager.updatePassword(userDetails, encodedPassword);

        response.setStatus(HttpStatus.OK);
        response.setMessage("Password updated");
        response.setData(true);

        return response;
    }
}