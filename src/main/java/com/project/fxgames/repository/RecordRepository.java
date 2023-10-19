package com.project.fxgames.repository;

import com.project.fxgames.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecordRepository extends JpaRepository<Record, String> {

    Optional<Record> findByRecordId_Id(String id);


    @Modifying
    @Query(value =
            "DELETE FROM record WHERE id IN (" +
                    "SELECT id FROM (SELECT id, SUM(OCTET_LENGTH(message)) OVER (ORDER BY time DESC) AS cumulative_size" +
                    " FROM record WHERE user_id = ?1) subquery" +
                    " WHERE cumulative_size > ?3) AND id NOT LIKE ?2"
            , nativeQuery = true)
    void deleteRecords(String userId, String id, Long limit);

}
