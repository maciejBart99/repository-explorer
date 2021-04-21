package com.repositories.explorer.repositories.api;

import com.repositories.explorer.shared.api.handlers.ApiError;
import com.repositories.explorer.shared.dao.IRepositoryDao;
import com.repositories.explorer.shared.dao.RepositoryDaoFactory;
import com.repositories.explorer.shared.domain.Repository;
import com.repositories.explorer.shared.domain.RepositoryFetchException;
import com.repositories.explorer.shared.domain.RepositoryHost;
import com.repositories.explorer.shared.domain.UserNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "repositories")
public class UserRepositoriesController {

    @Autowired
    private RepositoryDaoFactory repositoryDaoFactory;

    @GetMapping("/{user}")
    @Operation(summary = "Get all repositories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Unknown problem occurred", content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    public ResponseEntity<List<RepositoryDto>> getUserRepositoryList(@PathVariable(required = true) String user)
            throws RepositoryFetchException, UserNotFoundException {
        // easy to change host
        IRepositoryDao repositoryDao = repositoryDaoFactory.getRepositoryDao(RepositoryHost.Github);
        List<Repository> repositories = repositoryDao.getUserRepositories(user);
        List<RepositoryDto> dtos = repositories.stream().map(RepositoryDto::fromRepository).collect(Collectors.toList());
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
}
