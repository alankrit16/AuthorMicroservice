package com.service.author.Controller;

import com.service.author.Response.MappedResponse;
import com.service.author.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class AuthorController {
    private final AuthorService authorService;
    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/registerAuthor")
    Mono<Map<String,Object>> registerAuthor(@RequestBody HashMap<String,Object> request){
        return authorService.registerAuthor(request)
                .flatMap(
                        response->{
                            Map<String,Object> responseBody = MappedResponse.responseMap(
                                    201,"SUCCESS",response.get("response"));
                            return Mono.just(responseBody);
                        })
                .onErrorResume(error->{
                    Map<String,Object> errorBody = MappedResponse.responseMap(
                            500,"FAILED",error.getMessage());
                    return  Mono.just(errorBody);
                });
    }

    @GetMapping("/author/{authorId}")
    public Mono<Map<String,Object>> findAuthorByAuthorId(@PathVariable String authorId){
        return authorService.findUserByAuthorId(authorId)
                .flatMap(response->{
                    Map<String,Object> responseBody = MappedResponse.responseMap(
                            200,"SUCCESS",response.get("response"));
                    return Mono.just(responseBody);
                })
                .onErrorResume(error->{
                    Map<String,Object> errorBody = MappedResponse.responseMap(
                            500,"FAILED",error.getMessage());
                    return  Mono.just(errorBody);
                });
    }
}
