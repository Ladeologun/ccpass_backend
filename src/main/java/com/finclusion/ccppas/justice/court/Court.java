package com.finclusion.ccppas.justice.court;


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
public class Court {

    @Id
    @GeneratedValue
    @Column(name = "court_id")
    private Long id;
    private String courtNumber;
    private String name;
    private String courtClark;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="address", column=@Column(name="address")),
            @AttributeOverride(name="geoLocation", column=@Column(name="geo_location"))
    })
    private CourtLocation location;

    @OneToMany(mappedBy = "court")
    private List<CrimeCase> cases;

    @CreatedDate
    @Column(name = "created_at",nullable = false,updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name = "updated_at",nullable = false)
    private LocalDateTime updatedAt;
}
