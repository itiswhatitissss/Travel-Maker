package org.zerock.travelmaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.zerock.travelmaker.domain.Friend;
import org.zerock.travelmaker.domain.Users;

import java.util.List;
import java.util.Map;

public interface FriendRepository extends JpaRepository<Friend,Long> {

    @Modifying
    @Query("delete from Friend f where f.unoByFriend=:uno and f.fnoByFriend=:fno")
    void friendDelete(Users uno, Users fno);


}
