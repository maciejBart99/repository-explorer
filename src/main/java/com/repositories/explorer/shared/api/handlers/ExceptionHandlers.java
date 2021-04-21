package com.repositories.explorer.shared.api.handlers;

import com.repositories.explorer.shared.domain.RepositoryFetchException;
import com.repositories.explorer.shared.domain.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFound(UserNotFoundException exception)
    {
        return new ResponseEntity<>(new ApiError(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RepositoryFetchException.class)
    public ResponseEntity<ApiError> handleFetchFailed(RepositoryFetchException exception)
    {
        return new ResponseEntity<>(new ApiError(exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
