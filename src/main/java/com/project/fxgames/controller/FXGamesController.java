package com.project.fxgames.controller;

import com.project.fxgames.service.FXGamesService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping
public class FXGamesController {
    public final static String POST_URL = "/fxgames/set/{userId}/{id}";
    public final static String GET_URL = "/fxgames/get/{userId}/{id}";

    private final FXGamesService fxGamesService;

    @GetMapping(GET_URL)
    public ResponseEntity<?> getRecord(@PathVariable("userId") String userId, @PathVariable("id") String id) {
        return ResponseEntity.ok(fxGamesService.getRecord(userId, id));
    }

    @PostMapping(POST_URL)
    public ResponseEntity<?> setRecord(@PathVariable("userId") String userId,
                                       @PathVariable("id") String id,
                                       @RequestBody String message) {
        return ResponseEntity.ok(fxGamesService.changeRecord(userId, id, message));
    }
}
