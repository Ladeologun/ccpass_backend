package com.finclusion.ccppas.crimecase.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Offense {

    @Id
    @GeneratedValue
    @Column(name = "offense_id")
    private Long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private OffenseType type;

    @OneToMany(mappedBy = "offense")
    private List<CrimeCase> crimeCases;

    @CreatedDate
    @Column(name = "created_at",nullable = false,updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name = "updated_at",nullable = false)
    private LocalDateTime updatedAt;
}
