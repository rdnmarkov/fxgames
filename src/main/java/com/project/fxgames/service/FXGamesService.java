package com.project.fxgames.service;

import com.project.fxgames.entity.Record;

public interface FXGamesService {

    public Record getRecord(String userId, String id);

    public Record changeRecord(String userId, String id, String message);

}
