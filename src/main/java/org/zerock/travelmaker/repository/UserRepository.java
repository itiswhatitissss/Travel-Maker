package org.zerock.travelmaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.travelmaker.domain.User;
import org.zerock.travelmaker.domain.UserLogin;

public interface UserRepository extends JpaRepository<User,Long> {
}
