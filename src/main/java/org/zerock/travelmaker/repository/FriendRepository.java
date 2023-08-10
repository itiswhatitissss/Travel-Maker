package org.zerock.travelmaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.travelmaker.domain.Friend;

public interface FriendRepository extends JpaRepository<Friend,Long> {
}
