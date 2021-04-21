package com.repositories.explorer.repositories.api;

import com.repositories.explorer.shared.domain.Repository;

public class RepositoryDto {
    private String userName;
    private int stars;

    public RepositoryDto(String userName, int stars) {
        this.userName = userName;
        this.stars = stars;
    }

    // in this case this dto class is not necessary, but it might help in future
    public static RepositoryDto fromRepository(Repository repository) {
        return new RepositoryDto(repository.getName(), repository.getStars());
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}
