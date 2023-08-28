package org.zerock.travelmaker.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.zerock.travelmaker.domain.Users;
import org.zerock.travelmaker.dto.FriendDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface MybatisMapper {
    public List<Map<String,Object>> getPartyList(Long uno);
    public List<Map<String,Object>> getPlanList(Long pno);
    public List<Map<String,Object>> selectFriendList(Long uno);
    public List<Map<String,Object>> searchFriend(String id);
    public Long getPartyOne(Long pno);

    public List<Map<String, Object>> getPlanOne(Long pno, Long plno);

}