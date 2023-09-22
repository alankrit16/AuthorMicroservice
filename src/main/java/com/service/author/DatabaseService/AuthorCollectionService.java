package com.service.author.DatabaseService;

import com.service.author.Model.AuthorsDAO;
import reactor.core.publisher.Mono;

public interface AuthorCollectionService {

    Mono<AuthorsDAO> registerUser(AuthorsDAO authors);
    Mono<AuthorsDAO> findUserByAuthorId(String authorId);
}
