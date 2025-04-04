//package com.finclusion.ccppas.justice.department;
//
//import com.finclusion.ccppas.crimecase.models.CrimeCase;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.List;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Entity
//public class JusticeDepartment {
//
//    @Id
//    @GeneratedValue
//    @Column(name = "department_id")
//    private Long id;
//    private String name;
//
//    @OneToMany(mappedBy = "department")
//    private List<CrimeCase> crimeCases;
//}
