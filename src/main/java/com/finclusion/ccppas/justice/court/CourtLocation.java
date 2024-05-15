package com.finclusion.ccppas.justice.court;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class CourtLocation {

    private String address;
    private String localGovernment;
    private String geoLocation;
    private CourtLocationState state;
}
