package com.project.fxgames.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Record {

    @EmbeddedId
    private RecordId recordId;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "time", nullable = false)
    private LocalDateTime time;


}
