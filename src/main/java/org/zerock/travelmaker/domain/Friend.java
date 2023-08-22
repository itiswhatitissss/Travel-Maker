package org.zerock.travelmaker.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "friendPK")
public class Friend{

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long friendPK;

    @ManyToOne
    @JoinColumn(name = "uno",referencedColumnName = "uno")
    private Users unoByFriend;

    @ManyToOne
    @JoinColumn(name = "fno",referencedColumnName = "uno")
    private Users fnoByFriend;

    public void changeFno(Users fnoByFriend) {
        this.fnoByFriend = fnoByFriend;
    }


}
