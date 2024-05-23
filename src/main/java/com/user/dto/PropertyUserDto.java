package com.user.dto;

import jakarta.validation.constraints.*;

import java.util.Date;

public class PropertyUserDto {
    private Long id;

    @NotEmpty
//    @Min(value = 2,message = "Should be at least 2 characters!")
    private String name;

    @Size(min=10,max=10 , message="Should be exactly 10 digits")
    private String mobile;

    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Email
    private String emailId;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
