package com.softuni.xmlproductshop.models.dtos.taskdtos;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserWrapperSellsDto {

    @XmlAttribute(name = "count")
    private Integer userCount;

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
