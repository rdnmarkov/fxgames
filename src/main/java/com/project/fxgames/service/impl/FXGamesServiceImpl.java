package com.project.fxgames.service.impl;

import com.project.fxgames.entity.Record;
import com.project.fxgames.entity.RecordId;
import com.project.fxgames.exception.DataNotFoundException;
import com.project.fxgames.repository.RecordRepository;
import com.project.fxgames.service.FXGamesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FXGamesServiceImpl implements FXGamesService {

    private final RecordRepository  recordRepository;

    @Override
    public Record getRecord(String userId, String id) {
        return recordRepository.findByRecordId_Id(id).orElseThrow(() -> new DataNotFoundException(id));
    }

    @Override
    public Record changeRecord(String userId, String id, String message) {
        Record record = Record.builder()
                .recordId(RecordId.builder().id(id).userId(userId).build())
                .message(message)
                .time(LocalDateTime.now()).build();
        return recordRepository.save(record);
    }
}
