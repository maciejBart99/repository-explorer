package com.repositories.explorer.repositories.api;

import com.repositories.explorer.shared.dao.IRepositoryDao;
import com.repositories.explorer.shared.dao.RepositoryDaoFactory;
import com.repositories.explorer.shared.domain.Repository;
import com.repositories.explorer.shared.domain.RepositoryHost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/repositories")
public class UserRepositoriesController {

    @Autowired
    private RepositoryDaoFactory repositoryDaoFactory;

    @GetMapping("/{user}")
    public ResponseEntity<List<RepositoryDto>> getUserRepositoryList(@PathVariable(required = true) String user) {
        IRepositoryDao repositoryDao = repositoryDaoFactory.getRepositoryDao(RepositoryHost.Github);
        List<Repository> repositories = repositoryDao.getUserRepositories(user);
        List<RepositoryDto> dtos = repositories.stream().map(RepositoryDto::fromRepository).collect(Collectors.toList());
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
}
