package com.service.author.Service;

import com.service.author.DatabaseService.AuthorCollectionService;
import com.service.author.Model.AuthorsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthorServiceImpl implements AuthorService{

    private final AuthorCollectionService authorCollectionService;
    @Autowired
    public AuthorServiceImpl(AuthorCollectionService authorCollectionService) {
        this.authorCollectionService = authorCollectionService;
    }

    @Override
    public Mono<Map<String, Object>> registerAuthor(HashMap<String, Object> request) {
        AuthorsDAO authorsDAO = new AuthorsDAO();
        authorsDAO.setAuthorId(request.get("authorId").toString());
        authorsDAO.setAuthorName(request.get("authorName").toString());
        return authorCollectionService.registerUser(authorsDAO)
                .flatMap(responseObj -> {
                    Map<String, Object> response = new HashMap<>();
                    response.put("response", responseObj);
                    return Mono.just(response);
                }).onErrorResume(Mono::error);
    }

    @Override
    public Mono<Map<String, Object>> findUserByAuthorId(String request) {
        return authorCollectionService.findUserByAuthorId(request)
                .flatMap(responseObj -> {
                    Map<String, Object> response = new HashMap<>();
                    response.put("response", responseObj);
                    return Mono.just(response);
                }).onErrorResume(Mono::error);
    }


}
