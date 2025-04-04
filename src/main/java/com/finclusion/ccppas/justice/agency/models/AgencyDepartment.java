package com.finclusion.ccppas.justice.agency.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class AgencyDepartment {

    @Id
    @GeneratedValue
    @Column(name = "department_id")
    private Long id;
    private String name;
    private String code;

    @ManyToOne
    @JoinColumn(name = "agency_id")
    private Agency agency;

    @CreatedDate
    @Column(name = "created_at",nullable = false,updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name = "updated_at",nullable = false)
    private LocalDateTime updatedAt;
}
