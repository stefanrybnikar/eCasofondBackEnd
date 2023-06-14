package com.pwc.ecasofond.request.body.add;

import com.pwc.ecasofond.request.body.Body;

public class AddProfessionTypeBody extends Body {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
