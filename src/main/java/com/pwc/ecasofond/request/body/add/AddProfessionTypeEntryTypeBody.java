package com.pwc.ecasofond.request.body.add;

import com.pwc.ecasofond.request.body.Body;

public class AddProfessionTypeEntryTypeBody extends Body {
    Long professionId;
    Long entryTypeId;

    public Long getProfessionId() {
        return professionId;
    }

    public Long getEntryTypeId() {
        return entryTypeId;
    }

    public void setProfessionId(Long professionId) {
        this.professionId = professionId;
    }

    public void setEntryTypeId(Long entryTypeId) {
        this.entryTypeId = entryTypeId;
    }
}
