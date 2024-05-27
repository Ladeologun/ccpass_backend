package com.finclusion.ccppas.user.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@MappedSuperclass
@SuperBuilder
@EntityListeners(AuditingEntityListener.class)
public abstract class BasicUser implements UserDetails, Principal {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true,length = 20)
    private String uniqueId;
    @Column(length = 20)
    private String firstname;
    @Column(length = 20)
    private String lastname;
    @Column(length = 20)
    private String phone;

    @Column(unique = true)
    private String email;
    private String password;
    @Column(length = 20)
    private String nin;
    @Column(length = 20)
    private String bvn;
    private String profile_picture;
    private String device_token;
    private String device_imei;
    private String allowed_ip_address;

    @Enumerated(EnumType.STRING)
    private UserStatus status;
    private LocalDate dateOfBirth;
    private LocalDate dateOfEngagement;

    @CreatedDate
    @Column(name = "created_at",nullable = false,updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name = "updated_at",nullable = false)
    private LocalDateTime updatedAt;


    public BasicUser(String unique_id, String firstname, String lastname, String email) {
        this.uniqueId = unique_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }
}
