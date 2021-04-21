package com.repositories.explorer.stars;

import com.repositories.explorer.shared.dao.IRepositoryDao;
import com.repositories.explorer.shared.dao.RepositoryDaoFactory;
import com.repositories.explorer.shared.domain.Repository;
import com.repositories.explorer.shared.domain.RepositoryHost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StarsService {

    @Autowired
    private RepositoryDaoFactory repositoryDaoFactory;

    public int getStars(String userName) {
        IRepositoryDao repositoryDao = repositoryDaoFactory.getRepositoryDao(RepositoryHost.Github);
        return repositoryDao.getUserRepositories(userName).stream().mapToInt(Repository::getStars).sum();
    }
}
