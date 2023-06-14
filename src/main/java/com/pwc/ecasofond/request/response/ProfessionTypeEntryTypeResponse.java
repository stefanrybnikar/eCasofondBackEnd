package com.pwc.ecasofond.request.response;

public class ProfessionTypeEntryTypeResponse extends Response {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProfessionTypeId() {
        return professionTypeId;
    }

    public void setProfessionTypeId(Long professionTypeId) {
        this.professionTypeId = professionTypeId;
    }

    public Long getEntryTypeId() {
        return entryTypeId;
    }

    public void setEntryTypeId(Long entryTypeId) {
        this.entryTypeId = entryTypeId;
    }

    private Long professionTypeId;
    private Long entryTypeId;
}
