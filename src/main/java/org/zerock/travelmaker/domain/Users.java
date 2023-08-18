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
public class Users {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="uno")
    private Long uno;

    private String id;
    private String name;
    private String password;
    private String email;
    private String address;
    private String phone;

    @OneToMany(mappedBy = "unoByFriend")
    private List<Friend> unofriends = new ArrayList<>();

    @OneToMany(mappedBy = "fnoByFriend")
    private List<Friend> fnofriends = new ArrayList<>();

    @OneToMany(mappedBy = "unoByUserParty")
    private List<UserParty> userParties = new ArrayList<>();

    @OneToMany(mappedBy = "unoByVoteOption")
    private List<VoteOption> voteOptions = new ArrayList<>();

}
