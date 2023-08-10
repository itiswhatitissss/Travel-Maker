package org.zerock.travelmaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.travelmaker.domain.UserParty;

public interface UserPartyRepository extends JpaRepository<UserParty,Long> {
}
