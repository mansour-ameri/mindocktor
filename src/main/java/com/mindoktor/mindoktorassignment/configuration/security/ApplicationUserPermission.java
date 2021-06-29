package com.mindoktor.mindoktorassignment.configuration.security;

public enum ApplicationUserPermission {
    OFFICER_READ("officer:read"),
    OFFICER_WRITE("officer:write"),
    INCIDENT_READ("incident:read"),
    INCIDENT_WRITE("incident:write"),
    INCIDENT_MARK("incident:mark"),
    REPORT_READ("report:read");

    private final String permission;

    ApplicationUserPermission(String permission){
        this.permission = permission;
    }

    public String getPermission(){
        return this.permission;
    }
}
