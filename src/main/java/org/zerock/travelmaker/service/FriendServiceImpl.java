package org.zerock.travelmaker.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;
import org.zerock.travelmaker.domain.Friend;
import org.zerock.travelmaker.domain.Plan;
import org.zerock.travelmaker.domain.Users;
import org.zerock.travelmaker.dto.FriendDTO;
import org.zerock.travelmaker.mapper.MybatisMapper;
import org.zerock.travelmaker.repository.FriendRepository;
import org.zerock.travelmaker.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class FriendServiceImpl implements FriendService{
    private final FriendRepository friendRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final MybatisMapper mybatisMapper;

    @Override
    public List<Map<String, Object>> friendList(Long uno) {
        List<Map<String, Object>> result =mybatisMapper.selectFriendList(uno);

        return result;
    }
    @Override
    public void insertFriend(FriendDTO friendDTO) {
        Optional<Users> byId = userRepository.findById(friendDTO.getUno());
        Users uno = byId.orElseThrow();
        Optional<Users> byId2 = userRepository.findById(friendDTO.getFno());
        Users fno = byId2.orElseThrow();

        Friend friend = Friend.builder()
                .fnoByFriend(fno)
                .unoByFriend(uno)
                .build();

        Friend friend1 = Friend.builder()
                .fnoByFriend(uno)
                .unoByFriend(fno)
                .build();

        log.info("fno ==============> {}", friend.getFnoByFriend());
        friendRepository.save(friend);
        friendRepository.save(friend1);
    }

    @Override
    public void deleteFriend(Long uno, Long fno) {
        Optional<Users> byId = userRepository.findById(uno);
        Users uno1 = byId.orElseThrow();
        Optional<Users> byId2 = userRepository.findById(fno);
        Users fno1 = byId2.orElseThrow();

        friendRepository.friendDelete(uno1, fno1);
        friendRepository.friendDelete(fno1, uno1);
    }
}
