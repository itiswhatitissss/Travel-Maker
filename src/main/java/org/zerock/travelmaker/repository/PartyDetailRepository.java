package org.zerock.travelmaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.travelmaker.domain.PartyDetail;

public interface PartyDetailRepository extends JpaRepository<PartyDetail,Long> {
}
