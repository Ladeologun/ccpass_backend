package com.finclusion.ccppas.justice.task;

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
public class JusticeTask {

    @Id
    @GeneratedValue
    @Column(name = "task_id")
    private Long id;

    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private JusticeTaskStatus status;

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
