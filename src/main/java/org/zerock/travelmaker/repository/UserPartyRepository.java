package org.zerock.travelmaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.zerock.travelmaker.domain.Party;
import org.zerock.travelmaker.domain.UserParty;
import org.zerock.travelmaker.domain.Users;

public interface UserPartyRepository extends JpaRepository<UserParty,Long> {
    @Modifying
    @Query("delete from UserParty U where U.unoByUserParty=:uno and U.pnoByUserParty=:pno")
    void partyDelete(Users uno, Party pno);
}
