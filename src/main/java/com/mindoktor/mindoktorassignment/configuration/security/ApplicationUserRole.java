package com.mindoktor.mindoktorassignment.configuration.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static com.mindoktor.mindoktorassignment.configuration.security.ApplicationUserPermission.*;
import java.util.Set;
import java.util.stream.Collectors;

public enum ApplicationUserRole {
    OFFICER(Sets.newHashSet(OFFICER_READ,OFFICER_WRITE,INCIDENT_MARK,INCIDENT_READ,REPORT_READ)),
    ADMIN(Sets.newHashSet(OFFICER_READ,OFFICER_WRITE,INCIDENT_MARK,INCIDENT_READ,INCIDENT_WRITE,REPORT_READ)),
    ANONYMOUS(Sets.newHashSet(INCIDENT_WRITE,REPORT_READ));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions){
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions(){
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());

        permissions.add(new SimpleGrantedAuthority("ROLE" + this.name()));
        return permissions;
    }

}
