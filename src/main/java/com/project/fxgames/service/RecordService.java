package com.project.fxgames.service;

import com.project.fxgames.entity.Record;

public interface RecordService {

    Record getRecordById(String id);

    Record saveRecordAndDelete(Record record);

}
