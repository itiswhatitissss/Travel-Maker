package org.zerock.travelmaker.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.travelmaker.domain.Friend;
import org.zerock.travelmaker.domain.Plan;
import org.zerock.travelmaker.dto.FriendDTO;
import org.zerock.travelmaker.repository.FriendRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class FriendServiceImpl implements FriendService{
    private final FriendRepository friendRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<Map<String, Object>> friendList(Long uno) {
        List<Map<String, Object>> result =friendRepository.selectAllFriend(uno);

        return result;
    }
    @Override
    public void insertFriend(FriendDTO friendDTO) {
        Friend friend = modelMapper.map(friendDTO, Friend.class);
        friendRepository.save(friend);
    }

    @Override
    public void deleteFriend(Long uno, Long fno) {
        friendRepository.friendDelete(uno, fno);
    }
}
