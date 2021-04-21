package com.repositories.explorer.stars.api;

public class StarsDto {
    private int stars;

    public StarsDto(int stars) {
        this.stars = stars;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}
