package org.zerock.travelmaker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zerock.travelmaker.domain.User;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long uno;
    private String id;
    private String name;
    private String password;
    private String email;
    private String address;
    private String phone;

    public User toEntity() {
        return User.builder()
                .uno(uno)
                .id(id)
                .name(name)
                .password(password)
                .email(email)
                .address(address)
                .phone(phone)
                .build();
    }
}
