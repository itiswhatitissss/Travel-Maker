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

    @Override
    public List<Map<String, Object>> friendList(Long uno) {
        List<Map<String, Object>> result =friendRepository.selectAllFriend(uno);

        return result;
    }
    @Override
    public void insertFriend(FriendDTO friendDTO) {
//        Friend friend = friendMapper.map(friendDTO, Friend.class);  // testRegister 넘어온 DTO를 Entity로 변경해주는 함수
        Optional<Users> byId = userRepository.findById(friendDTO.getUno());
        Users uno = byId.orElseThrow();
        Optional<Users> byId2 = userRepository.findById(friendDTO.getFno());
        Users fno = byId2.orElseThrow();

        Friend friend = Friend.builder()
                .fnoByFriend(fno)
                .unoByFriend(uno)
                .build();

        log.info("fno ==============> {}", friend.getFnoByFriend());
        friendRepository.save(friend);
    }

    @Override
    public void deleteFriend(Long uno, Long fno) {
        friendRepository.friendDelete(uno, fno);
    }
}
