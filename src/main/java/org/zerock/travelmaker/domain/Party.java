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
@ToString(exclude = "pno")
public class Party{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pno")
    private Long pno;

    @Column(length = 50,nullable = false)
    private String partyName;

    @ManyToOne
    @JoinColumn(name="uno")
    private UserLogin userLogin;

    @OneToMany(mappedBy = "party1")
    private List<User> users1 = new ArrayList<>();

    @OneToMany(mappedBy = "party2")
    private List<User> users2 = new ArrayList<>();

}
