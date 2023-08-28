package org.zerock.travelmaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.travelmaker.domain.Party;

import java.util.List;

public interface PartyRepository extends JpaRepository<Party,Long> {
    List<Party> findByPartyNameContainingIgnoreCase(String value);
}
