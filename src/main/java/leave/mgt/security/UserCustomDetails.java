package leave.mgt.security;


import leave.mgt.model.Role;
import leave.mgt.model.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class UserCustomDetails implements UserDetails {
    private Users theUser;

    public UserCustomDetails(Users theUser) {
        this.theUser = theUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = theUser.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return theUser.getPassword();
    }

    @Override
    public String getUsername() {
        return theUser.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return theUser.isActive();
    }

    public String getFullnames(){
        return theUser.getFirstname()+" "+theUser.getLastname();
    }
    public Integer getUserId(){
        return theUser.getId();
    }

    public Users getUser() {
        return theUser;
    }
//    public String userRole(){
//        return theUser.
//    }
    public boolean hasRole(String roleName) {
        for(Role role : theUser.getRoles()){
            if(role.getName().equals(roleName)){
                return true;
            }
        }
        return false;
    }
}
