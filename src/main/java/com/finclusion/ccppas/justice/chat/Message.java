package com.finclusion.ccppas.justice.chat;


import com.finclusion.ccppas.user.models.JusticePractitioner;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Message {

    @Id
    @GeneratedValue
    @Column(name = "message_id")
    private Long id;
    private Boolean isRead;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private JusticePractitioner sender;
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private JusticePractitioner receiver;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;

    @OneToOne
    @JoinColumn(name = "attachment_id")
    private ChatAttachment attachment;
}
