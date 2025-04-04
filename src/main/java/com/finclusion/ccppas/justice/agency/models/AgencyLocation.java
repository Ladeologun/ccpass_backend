package com.finclusion.ccppas.justice.agency.models;


import com.finclusion.ccppas.justice.global.models.LocationState;
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
public class AgencyLocation {

    @Id
    @GeneratedValue
    @Column(name = "location_id")
    private Long id;
    private String code;
    private String address;
    private String localGovernment;
    private String geoLocation;

    @Enumerated(EnumType.STRING)
    private LocationState state;

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
