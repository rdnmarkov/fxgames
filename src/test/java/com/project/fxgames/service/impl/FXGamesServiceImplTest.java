package com.project.fxgames.service.impl;

import com.project.fxgames.entity.Record;
import com.project.fxgames.entity.RecordId;
import com.project.fxgames.service.RecordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class FXGamesServiceImplTest {

    @Mock
    private RecordService recordService;

    @InjectMocks
    private FXGamesServiceImpl fxGamesService;

    @Test
    public void testGetRecord() {

        String userId = "user123";
        String recordId = "record456";
        Record record = Record.builder()
                .recordId(RecordId.builder().id(recordId).userId(userId).build())
                .message("Test message")
                .time(LocalDateTime.now())
                .build();
        when(recordService.getRecordById(recordId)).thenReturn(record);

        Record result = fxGamesService.getRecord(userId, recordId);

        assertEquals(record, result);
    }

    @Test
    public void testChangeRecord() {
        String userId = "user123";
        String recordId = "record456";
        String message = "New message";
        Record record = Record.builder()
                .recordId(RecordId.builder().id(recordId).userId(userId).build())
                .message(message)
                .time(LocalDateTime.now())
                .build();
        when(recordService.saveRecordAndDelete(any())).thenReturn(record);

        Record result = fxGamesService.changeRecord(userId, recordId, message);

        assertEquals(record, result);
    }
}