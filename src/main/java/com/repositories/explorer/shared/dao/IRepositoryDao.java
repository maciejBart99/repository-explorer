package com.repositories.explorer.shared.dao;

import com.repositories.explorer.shared.domain.Repository;
import com.repositories.explorer.shared.domain.RepositoryFetchException;
import com.repositories.explorer.shared.domain.UserNotFoundException;

import java.util.List;

public interface IRepositoryDao {
    List<Repository> getUserRepositories(String userName) throws RepositoryFetchException, UserNotFoundException;
}
