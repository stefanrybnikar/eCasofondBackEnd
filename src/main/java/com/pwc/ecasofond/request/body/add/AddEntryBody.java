package com.pwc.ecasofond.request.body.add;

import com.pwc.ecasofond.request.body.Body;

import java.sql.Date;

public class AddEntryBody extends Body {
    Long userId;
    Long typeId;
    String description;
    Integer hourCount;
    Date day;

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
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
}
