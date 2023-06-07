package com.pwc.ecasofond.request.body.update;

import com.pwc.ecasofond.request.body.Body;

public class UpdateCompanyBody extends Body {
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
