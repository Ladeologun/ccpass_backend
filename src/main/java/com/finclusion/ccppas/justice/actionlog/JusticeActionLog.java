package com.finclusion.ccppas.justice.actionlog;


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
public class JusticeActionLog {

    @Id
    @GeneratedValue
    @Column(name = "action_log_id")
    private Long id;
    private String message;
    @Enumerated(EnumType.STRING)
    private JusticeActionLogType type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private JusticePractitioner user;
    @CreatedDate
    @Column(name = "created_at",nullable = false,updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name = "updated_at",nullable = false)
    private LocalDateTime updatedAt;
}
