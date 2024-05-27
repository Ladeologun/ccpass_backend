package com.finclusion.ccppas.user.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@SuperBuilder
@Entity
public class JusticePractitionerRole {

    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private Long id;
    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private List<JusticePractitioner> justicePractitioners;

    public JusticePractitionerRole(String name) {
        this.name = name;
    }
}
