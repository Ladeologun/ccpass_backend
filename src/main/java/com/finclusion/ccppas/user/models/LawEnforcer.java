package com.finclusion.ccppas.user.models;

import com.finclusion.ccppas.crimecase.models.CrimeCase;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@NoArgsConstructor
public class LawEnforcer extends BasicUser {

    @ManyToOne
    @JoinColumn(name = "role_id")
    private LawEnforcerRole role;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private LawEnforcerType type;

    @OneToMany(mappedBy = "prosecutor")
    private List<CrimeCase> prosecutedCases;

    public LawEnforcer(String unique_id, String firstname, String lastname, String email) {
        super(unique_id, firstname, lastname, email);
    }
}
