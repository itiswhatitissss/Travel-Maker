package org.zerock.travelmaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.travelmaker.domain.User;

public interface UserRepository extends JpaRepository<User,Long> {
}
