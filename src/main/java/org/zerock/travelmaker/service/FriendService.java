package org.zerock.travelmaker.service;

import org.zerock.travelmaker.dto.FriendDTO;

import java.util.List;
import java.util.Map;

public interface FriendService {
    List<Map<String, Object>> friendList(Long uno);
    List<Map<String, Object>> friendSearch(String id);

    void insertFriend(FriendDTO friendDTO);

    void deleteFriend(Long uno, Long fno);

}
