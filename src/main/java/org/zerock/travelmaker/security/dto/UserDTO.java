package org.zerock.travelmaker.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Data
@Builder
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
