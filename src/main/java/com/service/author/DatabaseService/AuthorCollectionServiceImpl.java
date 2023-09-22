package com.service.author.DatabaseService;

import com.service.author.Exception.NoRecordFoundException;
import com.service.author.Model.AuthorsDAO;
import com.service.author.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AuthorCollectionServiceImpl implements AuthorCollectionService{

    private final AuthorRepository authorRepository;
    @Autowired
    public AuthorCollectionServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Mono<AuthorsDAO> registerUser(AuthorsDAO authors) {
        return authorRepository.save(authors)
                .onErrorResume(Mono::error);
    }

    @Override
    public Mono<AuthorsDAO> findUserByAuthorId(String authorId) {
        return authorRepository.findUserByAuthorId(authorId)
                .switchIfEmpty(Mono.error(new NoRecordFoundException("No records found in DB")))
                .onErrorResume(Mono::error);
    }
}
