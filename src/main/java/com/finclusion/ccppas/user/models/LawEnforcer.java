package com.finclusion.ccppas.user.models;

import com.finclusion.ccppas.crimecase.models.CrimeCase;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@NoArgsConstructor
public class LawEnforcer extends BasicUser {

    @ManyToMany(fetch = FetchType.EAGER)
    private List<LawEnforcerRole> roles;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private LawEnforcerType type;

    @OneToMany(mappedBy = "prosecutor")
    private List<CrimeCase> prosecutedCases;

    public LawEnforcer(String unique_id, String firstname, String lastname, String email) {
        super(unique_id, firstname, lastname, email);
    }

    @Override
    public String getName() {
        return getUniqueId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return getUniqueId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !(getStatus() == UserStatus.LOCKED);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return getStatus() == UserStatus.ACTIVE;
    }
}
