package com.pwc.ecasofond.request.body.update;

import com.pwc.ecasofond.request.body.Body;

public class UpdateProfessionBody extends Body {
    Long id;
    String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
