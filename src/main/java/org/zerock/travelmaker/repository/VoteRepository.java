package org.zerock.travelmaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.travelmaker.domain.Party;
import org.zerock.travelmaker.domain.Vote;

import java.util.Date;

public interface VoteRepository extends JpaRepository<Vote,Long> {
    @Query("select COUNT(up) from UserParty up where up.pnoByUserParty=:pno")
    int countMember(Party pno);
//    @Query("select p.end from Plan p where p.plno=:plno")
//    Date selectEnd(Long plno);
}
