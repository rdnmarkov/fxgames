package com.project.fxgames.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/fxgames")
public class FXGamesController {
    private final static String POST_URL = "/set/{userId}/{id}";
    private final static String GET_URL = "/get/{userId}/{id}";

    @GetMapping(GET_URL)
    public ResponseEntity<?> getUser(@PathVariable("userId") String userId, @PathVariable("id") String id){

        return ResponseEntity.ok("OK");
    }

    @PostMapping(POST_URL)
    public ResponseEntity<?> setUser(@PathVariable("userId") String userId, @PathVariable("id") String id){

        return ResponseEntity.ok("OK");
    }
}
