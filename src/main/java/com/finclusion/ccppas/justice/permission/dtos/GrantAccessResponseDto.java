package com.finclusion.ccppas.justice.permission.dtos;


import com.finclusion.ccppas.justice.permission.models.JusticePermissionStatus;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GrantAccessResponseDto {
    private Long id;
    private JusticePermissionStatus status;
}
