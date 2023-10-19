package com.project.fxgames.repository;

import com.project.fxgames.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecordRepository extends JpaRepository<Record, String> {

    Optional<Record> findByRecordId_Id(String id);

}
