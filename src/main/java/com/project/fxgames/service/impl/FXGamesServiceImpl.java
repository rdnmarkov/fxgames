package com.project.fxgames.service.impl;

import com.project.fxgames.entity.Record;
import com.project.fxgames.entity.RecordId;
import com.project.fxgames.exception.BadRequestException;
import com.project.fxgames.service.FXGamesService;
import com.project.fxgames.service.RecordService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FXGamesServiceImpl implements FXGamesService {

    private final RecordService recordService;

    @Override
    public Record getRecord(String userId, String id) {
        return recordService.getRecordById(id);
    }

    @Override
    public Record changeRecord(String userId, String id, String message) {
        try {
            Record record = Record.builder()
                    .recordId(RecordId.builder().id(id).userId(userId).build())
                    .message(message)
                    .time(LocalDateTime.now()).build();

            return recordService.saveRecordAndDelete(record);
        } catch (Exception ex) {
            throw new BadRequestException(id);
        }
    }
}
