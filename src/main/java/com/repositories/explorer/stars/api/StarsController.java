package com.repositories.explorer.stars.api;

import com.repositories.explorer.shared.api.handlers.ApiError;
import com.repositories.explorer.shared.domain.RepositoryFetchException;
import com.repositories.explorer.shared.domain.RepositoryHost;
import com.repositories.explorer.shared.domain.UserNotFoundException;
import com.repositories.explorer.stars.StarsService;
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


@RestController
@RequestMapping("/api/stars")
@Tag(name = "stars")
public class StarsController {

    @Autowired
    private StarsService starsService;

    @GetMapping("/{user}")
    @Operation(summary = "Get total stars for user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Unknown problem occurred", content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    public ResponseEntity<StarsDto> getUserStars(@PathVariable(required = true) String user) throws UserNotFoundException, RepositoryFetchException {
        // easy to change host
        int stars = starsService.getStars(user, RepositoryHost.Github);
        StarsDto dto = new StarsDto(stars);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
