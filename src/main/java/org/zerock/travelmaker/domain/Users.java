package org.zerock.travelmaker.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
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

    @Column(unique = true)
    private String id;

    @Column(nullable = false)
    private String password;

    private String name;

    @Column(unique = true)
    private String email;

    private String code;
    private boolean del;
    private boolean social;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();


    @OneToMany(mappedBy = "unoByFriend")
    private List<Friend> unofriends = new ArrayList<>();

    @OneToMany(mappedBy = "fnoByFriend")
    private List<Friend> fnofriends = new ArrayList<>();

    @OneToMany(mappedBy = "unoByUserParty")
    private List<UserParty> userParties = new ArrayList<>();


    @OneToMany(mappedBy = "unoByAttend")
    private List<Attend> unoAttend = new ArrayList<>();

    public void changePassword(String password){this.password = password;}

    public void changeEmail(String email){
        this.email = email;
    }

    public void changeDel(boolean del){
        this.del = del;
    }

    public void addRole(MemberRole memberRole){
        this.roleSet.add(memberRole);
    }

    public void clearRoles() {
        this.roleSet.clear();
    }

    public void changeSocial(boolean social){this.social = social;}

}
