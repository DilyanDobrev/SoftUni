package com.softuni.springjson.models.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    private String firstName;
    private String lastName;
    private int age;
    private Set<User> friends;
    private Set<Product> bought;
    private Set<Product> sold;

    public User() {
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
    @Length(min = 3, message = "Wrong last name length.")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "age")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @ManyToMany(cascade = {ALL})
    @JoinTable(name = "users_friends",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id")
    )
    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    @OneToMany(mappedBy = "buyer")
    public Set<Product> getBought() {
        return bought;
    }

    public void setBought(Set<Product> bought) {
        this.bought = bought;
    }

    @OneToMany(mappedBy = "seller", fetch = EAGER)
    public Set<Product> getSold() {
        return sold;
    }

    public void setSold(Set<Product> sold) {
        this.sold = sold;
    }
}
