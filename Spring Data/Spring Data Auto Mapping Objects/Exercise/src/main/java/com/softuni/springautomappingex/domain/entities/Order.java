package com.softuni.springautomappingex.domain.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    private User user;
    private Set<Game> orderedGames;

    public Order() {
    }

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToMany
    @JoinTable(name = "orders_ordered_games",
            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ordered_game_id", referencedColumnName = "id"))
    public Set<Game> getOrderedGames() {
        return orderedGames;
    }

    public void setOrderedGames(Set<Game> orderedGames) {
        this.orderedGames = orderedGames;
    }
}


