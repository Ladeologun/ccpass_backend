package com.finclusion.ccppas.justice.chat;

import com.finclusion.ccppas.justice.global.models.Attachment;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@DiscriminatorValue("chatAttachment")
public class ChatAttachment extends Attachment {
    @OneToOne
    @JoinColumn(name = "message_id")
    private Message message;

}
