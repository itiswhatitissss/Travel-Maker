package org.zerock.travelmaker.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.zerock.travelmaker.domain.Users;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Long> {

    @Query("select u.uno from Users u where u.id=:id")
    Long findUno(String id);

//    @Query("select u from Users u where u.id = :username")
    Users findById(String username);

    @EntityGraph(attributePaths = "roleSet")
    Optional<Users> findByEmail(String email);

    @Modifying
    @Transactional
    @Query("update Users u set u.password =:password where u.id = :id ")
    void updatePassword(@Param("password") String password, @Param("id") String id);

}
