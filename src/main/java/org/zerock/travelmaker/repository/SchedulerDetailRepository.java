package org.zerock.travelmaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.travelmaker.domain.SchedulerDetail;

public interface SchedulerDetailRepository extends JpaRepository<SchedulerDetail,Long> {
}
