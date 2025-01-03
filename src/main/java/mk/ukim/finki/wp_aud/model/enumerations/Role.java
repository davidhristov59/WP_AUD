package mk.ukim.finki.wp_aud.model.enumerations;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    ROLE_USER,
    ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name(); //returns the name of the role, which is used by Spring Security for authorization purposes.
    }
}
