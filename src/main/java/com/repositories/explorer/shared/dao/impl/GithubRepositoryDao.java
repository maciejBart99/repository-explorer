package com.repositories.explorer.shared.dao.impl;

import com.repositories.explorer.shared.dao.IRepositoryDao;
import com.repositories.explorer.shared.domain.Repository;

import java.util.LinkedList;
import java.util.List;

public class GithubRepositoryDao implements IRepositoryDao {

    @Override
    public List<Repository> getUserRepositories(String userName) {
        return new LinkedList<>();
    }
}
