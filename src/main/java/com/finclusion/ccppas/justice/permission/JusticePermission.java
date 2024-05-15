package com.finclusion.ccppas.justice.permission;


import com.finclusion.ccppas.user.models.JusticePractitioner;
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
public class JusticePermission {

    @Id
    @GeneratedValue
    @Column(name = "permission_id")
    private Long id;
    @Enumerated(EnumType.STRING)
    private JusticePermissionStatus status;
    @Enumerated(EnumType.STRING)
    private JusticePermissionType type;
    private String notes;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private JusticePractitioner sender;
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private JusticePractitioner receiver;
    @CreatedDate
    @Column(name = "created_at",nullable = false,updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name = "updated_at",nullable = false)
    private LocalDateTime updatedAt;

}
