package org.zerock.travelmaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.travelmaker.domain.VoteOption;

public interface VoteOptionRepository extends JpaRepository<VoteOption,Long> {
}
