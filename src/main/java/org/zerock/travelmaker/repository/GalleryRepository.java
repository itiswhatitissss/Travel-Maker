package org.zerock.travelmaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.travelmaker.domain.Gallery;

public interface GalleryRepository extends JpaRepository<Gallery,Long> {
}
