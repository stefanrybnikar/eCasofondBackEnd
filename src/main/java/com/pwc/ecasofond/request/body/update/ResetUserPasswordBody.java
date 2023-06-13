package com.pwc.ecasofond.request.body.update;

import com.pwc.ecasofond.request.body.Body;

public class ResetUserPasswordBody extends Body {
    Long id;
    String oldPassword;
    String newPassword;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
