package com.project.fxgames.service.impl;

import com.project.fxgames.config.FXGamesProperties;
import com.project.fxgames.entity.Record;
import com.project.fxgames.entity.RecordId;
import com.project.fxgames.exception.BadRequestException;
import com.project.fxgames.exception.DataNotFoundException;
import com.project.fxgames.repository.RecordRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RecordServiceImplTest {

    @Mock
    private RecordRepository recordRepository;

    @Mock
    private FXGamesProperties fxGamesProperties;

    @InjectMocks
    private RecordServiceImpl recordService;

    @Test
    public void testGetRecordById() {

        String id = "123";
        Record record = new Record();
        record.setRecordId(RecordId.builder().id(id).build());
        when(recordRepository.findByRecordId_Id(id)).thenReturn(Optional.of(record));

        Record result = recordService.getRecordById(id);

        assertEquals(record, result);
    }

    @Test(expected = DataNotFoundException.class)
    public void testGetRecordByIdNotFound() {

        String id = "123";
        when(recordRepository.findByRecordId_Id(id)).thenReturn(Optional.empty());

        recordService.getRecordById(id);

    }

    @Test(expected = BadRequestException.class)
    public void testSaveRecordAndDeleteBadRequest() {

        Record record = new Record();
        record.setRecordId(new RecordId("123", "user1"));
        when(recordRepository.save(record)).thenThrow(BadRequestException.class);


        recordService.saveRecordAndDelete(record);

    }

    @Test
    public void testSaveRecordAndDelete() {

        Record record = new Record();
        record.setRecordId(new RecordId("123", "user1"));
        when(recordRepository.save(record)).thenReturn(record);
        when(fxGamesProperties.getLimit()).thenReturn(10L);

        Record result = recordService.saveRecordAndDelete(record);


        assertEquals(record, result);
        verify(recordRepository, times(1)).save(record);
        verify(recordRepository, times(1)).deleteRecords("user1", "123", 10L);
    }
}