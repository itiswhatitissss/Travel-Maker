package org.zerock.travelmaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.travelmaker.domain.Friend;

import java.util.List;
import java.util.Map;

public interface FriendRepository extends JpaRepository<Friend,Long> {
    @Query("select f from Friend f where f.unoByFriend=:uno")
    List<Map<String, Object>> selectAllFriend(Long uno);

    @Query("delete from Friend f where f.unoByFriend=:uno and f.fnoByFriend=:fno")
    void friendDelete(Long uno, Long fno);


}
