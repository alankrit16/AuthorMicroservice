package com.service.author.Service;

import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

public interface AuthorService {
    Mono<Map<String,Object>> registerAuthor(HashMap<String,Object> request);
    Mono<Map<String,Object>> findUserByAuthorId(String request);

}
