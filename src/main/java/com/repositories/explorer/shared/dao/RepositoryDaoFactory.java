package com.repositories.explorer.shared.dao;

import com.repositories.explorer.shared.dao.impl.GithubRepositoryDao;
import com.repositories.explorer.shared.domain.RepositoryHost;
import org.springframework.stereotype.Service;

@Service
public class RepositoryDaoFactory {
    public IRepositoryDao getRepositoryDao(RepositoryHost host) {
        if (host == RepositoryHost.Github) {
            return new GithubRepositoryDao();
        }
        return null;
    }
}
