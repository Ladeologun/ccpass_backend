package com.finclusion.ccppas.crimecase.models;

import com.finclusion.ccppas.justice.global.models.Attachment;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@DiscriminatorValue("caseAttachment")
public class CaseAttachment extends Attachment {

    @ManyToOne
    @JoinColumn(name = "case_id")
    private CrimeCase crimeCase;
}
