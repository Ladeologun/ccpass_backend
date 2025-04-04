package com.finclusion.ccppas.crimecase.models;


import com.finclusion.ccppas.justice.agency.models.Agency;
import com.finclusion.ccppas.justice.court.Court;
//import com.finclusion.ccppas.justice.department.JusticeDepartment;
import com.finclusion.ccppas.user.models.JusticePractitioner;
import com.finclusion.ccppas.user.models.LawEnforcer;
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
public class CrimeCase {

    @Id
    @GeneratedValue
    @Column(name = "case_id")
    private Long id;
    @Column(name = "case_number", unique = true)
    private String caseNumber;
    @Column(name = "court_number")
    private String courtNumber;

    @ManyToOne
    @JoinColumn(name = "offense_id")
    private Offense offense;

//    @ManyToOne
//    @JoinColumn(name = "department_id")
//    private JusticeDepartment department;

    @ManyToOne
    @JoinColumn(name = "agency_id")
    private Agency agency;

    @ManyToOne
    @JoinColumn(name = "court_id")
    private Court court;

    @ManyToOne
    @JoinColumn(name = "judge_id")
    private JusticePractitioner judge;
    private String chargeSheetNumber;

    @ManyToOne
    @JoinColumn(name = "prosecutor_id")
    private LawEnforcer prosecutor;

    private String accuserPictureUrl;
    private String accuserFullName;
    private String accuserAlias;
    private String knownAssociate;
    private String legalBases;
    private String particularsOfOffence;


    @Lob
    @Column(columnDefinition = "TEXT")
    private String chargeDetails;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String legalBrief;

    @OneToMany(mappedBy = "crimeCase")
    private List<CaseAttachment> attachments;

    @Enumerated(EnumType.STRING)
    private CrimeCaseStatus status;
    @Enumerated(EnumType.STRING)
    private AccuserStatus accuserStatus;

    @CreatedDate
    @Column(name = "date_initiated",nullable = false,updatable = false)
    private LocalDateTime dateInitiated;

    @CreatedDate
    @Column(name = "created_at",nullable = false,updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name = "updated_at",nullable = false)
    private LocalDateTime updatedAt;
}
