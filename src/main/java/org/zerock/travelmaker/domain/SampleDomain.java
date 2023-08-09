package org.zerock.travelmaker.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SampleDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bno;

    private String name;
}
