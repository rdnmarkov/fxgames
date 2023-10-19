package com.project.fxgames.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecordId implements Serializable {

    @Column(name = "id", unique = true)
    private String id;

    private String userId;
}
