package com.finclusion.ccppas.user.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class LawEnforcerRole {

    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private Long id;
    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "roles")
    @JsonIgnore
    private List<LawEnforcer> lawEnforcers;

    public LawEnforcerRole(String name) {
        this.name = name;
    }
}
