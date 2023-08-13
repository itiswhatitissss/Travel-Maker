package org.zerock.travelmaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.travelmaker.domain.Vote;

public interface VoteRepository extends JpaRepository<Vote,Long> {
}
