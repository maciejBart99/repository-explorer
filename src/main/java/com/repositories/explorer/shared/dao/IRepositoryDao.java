package com.repositories.explorer.shared.dao;

import com.repositories.explorer.shared.domain.Repository;

import java.util.List;

public interface IRepositoryDao {
    public List<Repository> getUserRepositories(String userName);
}
