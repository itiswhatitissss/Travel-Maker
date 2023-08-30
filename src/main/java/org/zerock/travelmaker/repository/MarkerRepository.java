package org.zerock.travelmaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.travelmaker.domain.Marker;

public interface MarkerRepository extends JpaRepository<Marker,Long> {
}
