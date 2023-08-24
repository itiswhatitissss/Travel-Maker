package org.zerock.travelmaker.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "uno")
public class Users{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="uno")
    private Long uno;

    private String id;
    private String password;
    private String name;
    private String email;

//    private String address;
//    private String phone;

    @OneToMany(mappedBy = "unoByFriend")
    private List<Friend> unofriends = new ArrayList<>();

    @OneToMany(mappedBy = "fnoByFriend")
    private List<Friend> fnofriends = new ArrayList<>();

    @OneToMany(mappedBy = "unoByUserParty")
    private List<UserParty> userParties = new ArrayList<>();

    @OneToMany(mappedBy = "unoByVoteOption")
    private List<VoteOption> voteOptions = new ArrayList<>();

    public void changePassword(String password){
        this.password = password;
    }
    public void changeName(String name){
        this.name = name;
    }
    public void changeEmail(String email){
        this.email = email;
    }
}
