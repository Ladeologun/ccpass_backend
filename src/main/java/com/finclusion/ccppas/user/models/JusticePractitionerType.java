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
public class JusticePractitionerType {

    @Id
    @GeneratedValue
    @Column(name = "type_id")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "type")
    private List<JusticePractitioner> practitioners;

    public JusticePractitionerType(String name) {
        this.name = name;
    }
}
