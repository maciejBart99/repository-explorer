package com.repositories.explorer.shared.domain;

public class RepositoryFetchException extends Exception {
    public RepositoryFetchException() {
        super("Repositories fetch failed due to unknown reason!");
    }
}
