package org.zerock.travelmaker.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class PersistentLogins {

    @Id
    private String series;
    private String username;
    private String token;
    private LocalDateTime lastUsed;

}
