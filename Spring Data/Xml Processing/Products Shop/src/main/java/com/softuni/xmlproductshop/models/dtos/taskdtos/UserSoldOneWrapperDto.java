package com.softuni.xmlproductshop.models.dtos.taskdtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserSoldOneWrapperDto {

    @XmlElement(name = "user")
    List<UserSoldOneDto> users;

    public UserSoldOneWrapperDto() {
        this.users = new ArrayList<>();
    }

    public List<UserSoldOneDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserSoldOneDto> users) {
        this.users = users;
    }
}
