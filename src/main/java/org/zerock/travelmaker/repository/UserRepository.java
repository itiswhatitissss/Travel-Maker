package org.zerock.travelmaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.travelmaker.domain.Users;

public interface UserRepository extends JpaRepository<Users,Long> {
}
