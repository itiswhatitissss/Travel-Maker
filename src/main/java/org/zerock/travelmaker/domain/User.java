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
public class User {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="uno")
    private Long uno;
    private String id;
    private String name;
    private String password;
    private String email;
    private String address;
    private String phone;

    @OneToMany(mappedBy = "uno")
    private List<Friend> friends1 = new ArrayList<>();

    @OneToMany(mappedBy = "fno")
    private List<Friend> friends2 = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserParty> userParties = new ArrayList<>();

}
