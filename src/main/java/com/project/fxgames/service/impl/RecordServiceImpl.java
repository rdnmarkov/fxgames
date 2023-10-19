package com.project.fxgames.service.impl;

import com.project.fxgames.config.FXGamesProperties;
import com.project.fxgames.entity.Record;
import com.project.fxgames.exception.DataNotFoundException;
import com.project.fxgames.repository.RecordRepository;
import com.project.fxgames.service.RecordService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class RecordServiceImpl implements RecordService {

    private final RecordRepository recordRepository;
    private final FXGamesProperties fxGamesProperties;

    @Override
    public Record getRecordById(String id) {
        return recordRepository.findByRecordId_Id(id).orElseThrow(() -> new DataNotFoundException(id));
    }

    @Override
    public Record saveRecordAndDelete(Record record) {
        Record saveRecord = recordRepository.save(record);

        recordRepository.deleteRecords(record.getRecordId().getUserId(),
                saveRecord.getRecordId().getId(),
                fxGamesProperties.getLimit());

        return saveRecord;
    }
}
