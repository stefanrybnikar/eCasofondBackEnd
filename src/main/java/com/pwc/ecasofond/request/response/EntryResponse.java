package com.pwc.ecasofond.request.response;

import java.sql.Timestamp;

public class EntryResponse extends Response {
    private Long id;
    private Long userId;
    private Long typeId;
    private String description;
    private Integer hourCount;
    private Timestamp created;
    private Timestamp updated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getHourCount() {
        return hourCount;
    }

    public void setHourCount(Integer hourCount) {
        this.hourCount = hourCount;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }
}
