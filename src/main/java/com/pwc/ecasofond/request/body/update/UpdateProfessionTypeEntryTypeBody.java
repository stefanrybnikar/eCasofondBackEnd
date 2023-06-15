package com.pwc.ecasofond.request.body.update;

import com.pwc.ecasofond.request.body.Body;

public class UpdateProfessionTypeEntryTypeBody extends Body {

    Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    Long professionTypeId;
    Long entryTypeId;

    public Long getProfessionTypeId() {
        return professionTypeId;
    }

    public Long getEntryTypeId() {
        return entryTypeId;
    }

    public void setProfessionTypeId(Long professionTypeId) {
        this.professionTypeId = professionTypeId;
    }

    public void setEntryTypeId(Long entryTypeId) {
        this.entryTypeId = entryTypeId;
    }
}
