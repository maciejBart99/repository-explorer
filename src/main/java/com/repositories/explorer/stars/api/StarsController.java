package com.repositories.explorer.stars.api;

import com.repositories.explorer.stars.StarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/stars")
public class StarsController {

    @Autowired
    private StarsService starsService;

    @GetMapping("/{user}")
    public ResponseEntity<StarsDto> getUserStars(@PathVariable(required = true) String user) {
        int stars = starsService.getStars(user);
        StarsDto dto = new StarsDto(stars);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
