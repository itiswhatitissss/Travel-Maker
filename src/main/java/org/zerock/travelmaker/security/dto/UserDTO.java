package org.zerock.travelmaker.security.dto;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
@ToString
public class UserDTO extends User {
    private Long uno;
    private String id;
    private String name;
    private String password;
    private String email;
    private String address;
    private String phone;

    public UserDTO(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);

        this.id= username;
        this.password= password;
    }
}
