package org.zerock.travelmaker.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "uno")
public class UserLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uno")
    private Long uno;

    @Column(length = 20, nullable = false, updatable = false)
    private String id;

    @Column(length = 10, nullable = false)
    private String name;

    @Column(length = 30, nullable = false)
    private String password;

    @Column(length = 40, nullable = false)
    private String email;

    @Column(length = 50,nullable = false)
    private String address;

    @Column(length = 50,nullable = false)
    private String phone;

    @OneToMany(mappedBy = "userLogin")
    private List<Party> parties = new ArrayList<>();

}