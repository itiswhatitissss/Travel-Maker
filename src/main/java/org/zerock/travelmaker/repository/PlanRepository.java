package org.zerock.travelmaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.travelmaker.domain.Plan;

public interface PlanRepository extends JpaRepository<Plan,Long> {
}
