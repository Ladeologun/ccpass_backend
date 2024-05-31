package com.finclusion.ccppas.justice.permission.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.finclusion.ccppas.justice.permission.models.JusticePermissionType;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestAccessResponseDto {

    private Long id;
    private JusticePermissionType type;
    private String senderEmail;
    private String senderFullname;
    private String supervisorEmail;
}
