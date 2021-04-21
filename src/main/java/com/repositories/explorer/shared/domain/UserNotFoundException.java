package com.repositories.explorer.shared.domain;

import java.text.MessageFormat;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String userName) {
        super(MessageFormat.format("User {0} not found!", userName));
    }
}
