package org.zerock.travelmaker.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.travelmaker.domain.Party;
import org.zerock.travelmaker.domain.Plan;
import org.zerock.travelmaker.domain.Users;

import java.util.List;

public interface PartyRepository extends JpaRepository<Party,Long> {
    List<Party> findByPartyNameContainingIgnoreCase(String value);

    @Transactional
    @Modifying
    @Query("UPDATE Party p SET p.partyName = :name where p.pno= :pno")
    void updateParty(@Param("name") String name, @Param("pno") Long pno);
}
