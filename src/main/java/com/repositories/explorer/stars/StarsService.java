package com.repositories.explorer.stars;

import com.repositories.explorer.shared.dao.IRepositoryDao;
import com.repositories.explorer.shared.dao.RepositoryDaoFactory;
import com.repositories.explorer.shared.domain.Repository;
import com.repositories.explorer.shared.domain.RepositoryFetchException;
import com.repositories.explorer.shared.domain.RepositoryHost;
import com.repositories.explorer.shared.domain.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StarsService {

    @Autowired
    private RepositoryDaoFactory repositoryDaoFactory;

    public int getStars(String userName, RepositoryHost host) throws UserNotFoundException, RepositoryFetchException {
        IRepositoryDao repositoryDao = repositoryDaoFactory.getRepositoryDao(host);
        return repositoryDao.getUserRepositories(userName).stream().mapToInt(Repository::getStars).sum();
    }
}
