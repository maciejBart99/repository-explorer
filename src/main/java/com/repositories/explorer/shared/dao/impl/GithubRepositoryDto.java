package com.repositories.explorer.shared.dao.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.repositories.explorer.shared.domain.Repository;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubRepositoryDto {
    private int stargazers_count;
    private String name;

    public Repository toRepository() {
        return new Repository(name, stargazers_count);
    }

    public int getStargazers_count() {
        return stargazers_count;
    }

    public void setStargazers_count(int stargazers_count) {
        this.stargazers_count = stargazers_count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
