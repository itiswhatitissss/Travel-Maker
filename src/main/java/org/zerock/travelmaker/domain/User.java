package org.zerock.travelmaker.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "uno")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uno", nullable = false, columnDefinition = "int")
    private Long uno;

    @Column(length = 20, nullable = false, updatable = false, unique = true)
    private String id;

    @Column(length = 10, nullable = false)
    private String name;

    @Column(length = 30, nullable = false)
    private String password;

    @Column(length = 40, nullable = false)
    private String email;

    @Column(length = 50,nullable = false)
    private String address;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "pno",referencedColumnName = "pno")
    Party party;


}