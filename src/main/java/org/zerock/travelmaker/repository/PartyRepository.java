package org.zerock.travelmaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.travelmaker.domain.Party;

public interface PartyRepository extends JpaRepository<Party,Long> {
}
