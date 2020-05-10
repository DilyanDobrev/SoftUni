package com.softuni.springautomappingex.domain.dtos;

import java.math.BigDecimal;

public class ViewAllGames {
    private String title;
    private BigDecimal price;

    public ViewAllGames() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
