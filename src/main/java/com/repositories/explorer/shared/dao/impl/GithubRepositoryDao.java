package com.repositories.explorer.shared.dao.impl;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.repositories.explorer.shared.dao.IRepositoryDao;
import com.repositories.explorer.shared.domain.Repository;
import com.repositories.explorer.shared.domain.RepositoryFetchException;
import com.repositories.explorer.shared.domain.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

public class GithubRepositoryDao implements IRepositoryDao {

    private final Logger logger = LoggerFactory.getLogger(GithubRepositoryDao.class);
    private final static String userRepositoriesUrl = "https://api.github.com/users/{0}/repos";

    @Override
    public List<Repository> getUserRepositories(String userName) throws RepositoryFetchException, UserNotFoundException {
        HttpResponse<byte[]> response = getUserRepositoriesHttp(userName);
        ObjectMapper mapper = new ObjectMapper();
        List<GithubRepositoryDto> githubRepositoryDtos = null;
        try {
            JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, GithubRepositoryDto.class);
            githubRepositoryDtos = mapper.readValue(response.body(), type);
        } catch (IOException ex) {
            logger.error(ex.getMessage() != null ? ex.getMessage() : "Parsing exception occurred!");
            throw new RepositoryFetchException();
        }

        return githubRepositoryDtos.stream().map(GithubRepositoryDto::toRepository).collect(Collectors.toList());
    }

    private HttpResponse<byte[]> getUserRepositoriesHttp(String userName)
            throws RepositoryFetchException, UserNotFoundException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(
                URI.create(MessageFormat.format(userRepositoriesUrl, userName)))
                .header("accept", "application/json")
                .build();
        HttpResponse<byte[]> response = null;
        try {
            logger.info("Calling GitHub api...");
            response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());
        } catch (IOException | InterruptedException ex) {
            logger.error(ex.getMessage() != null ? ex.getMessage() : "Fetch exception occurred!");
            throw new RepositoryFetchException();
        }
        if (response.statusCode() == HttpStatus.NOT_FOUND.value()) {
            throw new UserNotFoundException(userName);
        }
        if (response.statusCode() != HttpStatus.OK.value()) {
            throw new RepositoryFetchException();
        }
        return response;
    }
}
