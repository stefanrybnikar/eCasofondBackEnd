package com.pwc.ecasofond.request.response;

public class UserResponse extends Response {
    private Long id;
    private String username;
    private String email;
    private String displayName;
    private Long companyId;
    private Long roleId;
    private Long professionTypeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getProfessionTypeId() {
        return professionTypeId;
    }

    public void setProfessionTypeId(Long professionTypeId) {
        this.professionTypeId = professionTypeId;
    }
}
