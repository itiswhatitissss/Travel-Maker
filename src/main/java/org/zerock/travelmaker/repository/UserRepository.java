package org.zerock.travelmaker.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.travelmaker.domain.Users;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Long> {
    Optional<Users> findByIdAndPassword(String id, String password);

    @Query("select u.uno from Users u where u.id=:id and u.password=:password")
    Long findUno(String id, String password);


}
