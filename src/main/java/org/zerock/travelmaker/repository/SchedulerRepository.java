package org.zerock.travelmaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.travelmaker.domain.Scheduler;

public interface SchedulerRepository extends JpaRepository<Scheduler,Long> {
}
