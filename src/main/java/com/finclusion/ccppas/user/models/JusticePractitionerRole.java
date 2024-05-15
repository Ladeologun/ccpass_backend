package com.finclusion.ccppas.user.models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class JusticePractitionerRole {

    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "role")
    private List<JusticePractitioner> justicePractitioners;

    public JusticePractitionerRole(String name) {
        this.name = name;
    }
}
