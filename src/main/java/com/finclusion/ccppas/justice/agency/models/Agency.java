package com.finclusion.ccppas.justice.agency.models;

import com.finclusion.ccppas.crimecase.models.CrimeCase;
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
public class Agency {

    @Id
    @GeneratedValue
    @Column(name = "agency_id")
    private Long id;
    private String name;
    private String code;

    @OneToMany(mappedBy = "agency")
    private List<AgencyLocation> agencyLocations;

    @OneToMany(mappedBy = "agency")
    private List<AgencyDepartment> agencyDepartments;

    @OneToMany(mappedBy = "agency")
    private List<CrimeCase> crimeCases;

    @CreatedDate
    @Column(name = "created_at",nullable = false,updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name = "updated_at",nullable = false)
    private LocalDateTime updatedAt;

}
