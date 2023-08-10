package org.zerock.travelmaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.travelmaker.domain.UserLogin;

public interface UserLoginRepository extends JpaRepository<UserLogin,Long> {
}
