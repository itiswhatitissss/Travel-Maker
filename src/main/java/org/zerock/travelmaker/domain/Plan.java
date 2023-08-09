package org.zerock.travelmaker.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "plno")
public class Plan implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="plno")
    private Long plno;

    @ManyToOne
    @JoinColumn(name = "pno")
    private Party planPno;

    @Column(name="start", updatable = true)
    private Date start;

    @Column(name="end", updatable = true)
    private Date end;

    @Column(length = 50, nullable = false)
    private String title;
}
