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
public class LawEnforcerType {
    @Id
    @GeneratedValue
    @Column(name = "type_id")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "type")
    private List<LawEnforcer> lawEnforcers;

    public LawEnforcerType(String name) {
        this.name = name;
    }
}
