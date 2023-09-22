package com.service.author.Repository;

import com.service.author.Model.AuthorsDAO;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface AuthorRepository extends ReactiveMongoRepository<AuthorsDAO,String> {

    @Query("{'_id':?0}")
    Mono<AuthorsDAO> findUserByAuthorId(String id);
}
