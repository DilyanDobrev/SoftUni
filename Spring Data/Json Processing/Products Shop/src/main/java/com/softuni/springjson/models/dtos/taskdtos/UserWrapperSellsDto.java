package com.softuni.springjson.models.dtos.taskdtos;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class UserWrapperSellsDto {

    @Expose
    private Integer userCount;
    @Expose
    private List<UserSellsDto> users;

    public UserWrapperSellsDto() {
        this.users = new ArrayList<>();
    }

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    public List<UserSellsDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserSellsDto> users) {
        this.users = users;
    }
}
